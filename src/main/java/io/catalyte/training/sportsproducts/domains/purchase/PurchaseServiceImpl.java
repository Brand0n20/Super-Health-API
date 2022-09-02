package io.catalyte.training.sportsproducts.domains.purchase;

import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.product.ProductService;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import io.catalyte.training.sportsproducts.responseObjects.PurchasedProduct;
import io.catalyte.training.sportsproducts.responseObjects.UserPurchase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Immediate methods run by the RESTful purchase controllers
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {

  /**
   * An instance of the logger to keep track of errors
   */
  private final Logger logger = LogManager.getLogger(PurchaseServiceImpl.class);

  /**
   * An instance of the Purchase Repository Interface
   */
  PurchaseRepository purchaseRepository;

  /**
   * An instance of the Purchase Service Interface
   */
  ProductService productService;

  /**
   * An instance of the Line Item Interface
   */
  LineItemRepository lineItemRepository;

  /**
   * An instance of a Credit Card Validation
   */
  CreditCardValidation creditCardValidation = new CreditCardValidation();

  /**
   * Argument constructor to implement a Purchase Service
   *
   * @param purchaseRepository - A purchase repository object
   * @param productService     - A product service
   * @param lineItemRepository - A line item repository
   */
  @Autowired
  public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ProductService productService,
      LineItemRepository lineItemRepository) {
    this.purchaseRepository = purchaseRepository;
    this.productService = productService;
    this.lineItemRepository = lineItemRepository;
  }

  /**
   * Find all purchases with email linked to an email in the database
   *
   * @param email - the delivery address email in interest
   * @return all purchases linked to the given email
   */
  public List<Purchase> findPurchasesByEmail(String email) {
    try {
      return purchaseRepository.findByBillingAddressEmail(email);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  /**
   * Find all purchases associated with a users email address and then Build and return a
   * UserPurchase response
   *
   * @param email - The users email address
   * @return - A list of User Purchases
   */
  @Override
  public List<UserPurchase> findUserPurchasesByEmail(String email) {
    try {

      List<Purchase> userPurchasesData = purchaseRepository.findByUserEmail(email);

      List<UserPurchase> userPurchases = new ArrayList<>();
      for (Purchase purchase : userPurchasesData) {
        Collection<LineItem> items = purchase.getProducts();
        String purchaseDate = purchase.getPurchaseDate();
        long purchaseKey = purchase.getId();

        List<PurchasedProduct> products = new ArrayList<>();
        for (LineItem item : items) {
          PurchasedProduct purchasedProduct = new PurchasedProduct();
          Product product = item.getProduct();
          int quantity = item.getQuantity();
          long productKey = item.getId();
          double totalCost = (product.getPrice() * quantity);
          String name = product.getName();
          purchasedProduct.setProductKey(productKey);
          purchasedProduct.setName(name);
          purchasedProduct.setProductCost(totalCost);
          purchasedProduct.setQuantity(quantity);
          products.add(purchasedProduct);
        }

        double purchaseCost = 0;
        for (PurchasedProduct product : products) {
          purchaseCost += product.getProductCost();
        }
        UserPurchase userPurchaseResponse = new UserPurchase();
        userPurchaseResponse.setPurchaseKey(purchaseKey);
        userPurchaseResponse.setPurchaseDate(purchaseDate);
        userPurchaseResponse.setProducts(products);
        userPurchaseResponse.setPurchaseCost(purchaseCost);
        userPurchases.add(userPurchaseResponse);
      }
      return userPurchases;

    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  /**
   * Persists a purchase to the database
   *
   * @param newPurchase - the purchase to persist
   * @return the persisted purchase with ids
   */
  public Purchase savePurchase(Purchase newPurchase) {
    try {
      if (creditCardValidation.isValidCreditCard(newPurchase) && isActive(newPurchase)) {

        purchaseRepository.save(newPurchase);
      }
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    // after the purchase is persisted and has an id, we need to handle its lineitems and persist them as well
    handleLineItems(newPurchase);

    return newPurchase;
  }

  /**
   * Checks products within a purchase, and throws a 422 status if and products are inactive
   *
   * @param purchase - the purchase that contains products to check if they are active
   * @return true if all products are active
   */
  public boolean isActive(Purchase purchase) {
    Collection<LineItem> itemsList = purchase.getProducts();
    StringBuilder inactiveList = new StringBuilder();
    if (itemsList != null) {
      for (LineItem lineItem : itemsList) {
        Product product = productService.getProductById(lineItem.getProduct().getId());
        if (!product.getActive()) {
          inactiveList.append(product.getName() + ", ");
        }
      }
      if (inactiveList.length() != 0) {
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
            "This purchase contains inactive products: " + inactiveList);
      }
    }
    return true;
  }

  /**
   * This helper method retrieves product information for each line item and persists it
   *
   * @param purchase - the purchase object to handle lineitems for
   */
  private void handleLineItems(Purchase purchase) {

    Collection<LineItem> itemsList = purchase.getProducts();

    if (itemsList != null) {
      itemsList.forEach(lineItem -> {

        // retrieve full product information from the database
        Product product = productService.getProductById(lineItem.getProduct().getId());

        // set the product info into the lineitem
        if (product != null) {
          lineItem.setProduct(product);
        }

        // set the purchase on the line item
        lineItem.setPurchase(purchase);

        // persist the populated lineitem
        try {
          lineItemRepository.save(lineItem);
        } catch (DataAccessException e) {
          logger.error(e.getMessage());
          throw new ServerError(e.getMessage());
        }
      });

    }
  }
}

