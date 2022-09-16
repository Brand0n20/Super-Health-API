package io.catalyte.training.sportsproducts.domains.purchase;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.product.ProductService;
import io.catalyte.training.sportsproducts.exceptions.DataAccessError;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import io.catalyte.training.sportsproducts.responseObjects.PurchasedProduct;
import io.catalyte.training.sportsproducts.responseObjects.UserPurchase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  List<Purchase> testPurchases;

  @InjectMocks
  private PurchaseServiceImpl purchaseServiceImpl;

  @Mock
  private PurchaseRepository purchaseRepository;

  @Mock
  private LineItemRepository lineItemRepository;

  @Mock
  private ProductService productService;


  /**
   * A list of purchases is returned when an email is provided
   */
  @Test
  public void getPurchasesByEmailReturnsPurchases() {
    BillingAddress billingAddress = new BillingAddress();
    BillingAddress billingAddress2 = new BillingAddress();
    billingAddress.setEmail("bob@ross.com");
    billingAddress2.setEmail("blah");
    Purchase purchase1 = new Purchase();
    purchase1.setBillingAddress(billingAddress);
    Purchase purchase2 = new Purchase();
    purchase2.setBillingAddress(billingAddress);
    Purchase purchase3 = new Purchase();
    purchase3.setBillingAddress(billingAddress);
    Purchase purchase4 = new Purchase();
    purchase4.setBillingAddress(billingAddress2);
    testPurchases = Arrays.asList(purchase1, purchase2, purchase3, purchase4);
    when(purchaseRepository.findByBillingAddressEmail(anyString())).thenReturn(testPurchases);
    List<Purchase> actual = purchaseServiceImpl.findPurchasesByEmail(anyString());
    assertEquals(testPurchases, actual);
  }

  /**
   * findUserPurchasesByEmail() data access exception test
   *
   * @author - Andrew Salerno
   */
  @Test
  public void findUserPurchasesByEmailReturnsDataAccessExceptionMessage() {
    when(purchaseRepository.findByUserEmail(anyString())).thenThrow(
        new DataAccessError(""));
    try {
      purchaseServiceImpl.findUserPurchasesByEmail("asalerno@catalyte.io");
    } catch (ServerError e) {
      String actualServerMessage = e.getMessage();
      String expectedServerMessage = "";
      assertEquals(expectedServerMessage, actualServerMessage);
    }
  }

  /**
   * savePurchase() data access exception test
   *
   * @author - Andrew Salerno
   */
  @Test
  public void savePurchaseReturnsDataAccessExceptionMessage() {
    CreditCard creditCard = new CreditCard("1234567890123456", "234", "10/29", "Bob Ross");
    Purchase purchase = new Purchase();
    purchase.setCreditCard(creditCard);
    when(purchaseRepository.save(purchase)).thenThrow(
        new DataAccessError(""));
    try {
      purchaseServiceImpl.savePurchase(purchase);
    } catch (ServerError e) {
      String actualServerMessage = e.getMessage();
      String expectedServerMessage = "";
      assertEquals(expectedServerMessage, actualServerMessage);
    }
  }

  /**
   * findPurchasesByEmail() data access exception test
   *
   * @author - Andrew Salerno
   */
  @Test
  public void findPurchasesByEmailReturnsDataAccessExceptionMessage() {
    when(purchaseRepository.findByBillingAddressEmail(anyString())).thenThrow(
        new DataAccessError(""));
    try {
      purchaseServiceImpl.findPurchasesByEmail("asalerno@catalyte.io");
    } catch (ServerError e) {
      String actualServerMessage = e.getMessage();
      String expectedServerMessage = "";
      assertEquals(expectedServerMessage, actualServerMessage);
    }
  }

  /**
   * handleLineItems() data access exception test
   *
   * @author - Andrew Salerno
   */
  @Test
  public void handleLineItemsReturnsDataAccessExceptionMessage() {
    Collection<LineItem> items = new ArrayList<>();
    LineItem item = new LineItem();
    Product product = new Product();
    product.setId(100L);
    item.setProduct(product);

    Purchase purchase = new Purchase();
    items.add(item);
    purchase.setProducts(items);
    when(lineItemRepository.save(item)).thenThrow(
        new DataAccessError(""));
    when(productService.getProductById(100L)).thenReturn(product);
    try {
      purchaseServiceImpl.handleLineItems(purchase);
    } catch (ServerError e) {
      String actualServerMessage = e.getMessage();
      String expectedServerMessage = "";
      assertEquals(expectedServerMessage, actualServerMessage);
    }
  }

  /**
   * handleLineItems() adds item
   *
   * @author - Andrew Salerno
   */
  @Test
  public void handleLineItemsAddsItem() {
    Collection<LineItem> items = new ArrayList<>();
    LineItem item = new LineItem();
    Product product = new Product();
    product.setId(100L);
    item.setProduct(product);

    Purchase purchase = new Purchase();
    items.add(item);
    purchase.setProducts(items);
    when(productService.getProductById(100L)).thenReturn(product);
    purchaseServiceImpl.handleLineItems(purchase);
  }

  /**
   * Checks if getUserPurchasesByEmail() returns an empty list when the email address is not found
   *
   * @author - Andrew Salerno
   */
  @Test
  public void getUserPurchasesByEmailReturnsEmpty() {
    List<UserPurchase> actual = purchaseServiceImpl.findUserPurchasesByEmail(
        "asalerno@catalyte.io");
    List<UserPurchase> expected = new ArrayList<>();
    assertEquals(expected, actual);
  }

  /**
   * Checks if getUserPurchasesByEmail() returns the correct UserPurchase response
   *
   * @author - Andrew Salerno
   */
  @Test
  public void getUserPurchasesByEmailReturnsCorrectUserPurchase() {
    Purchase purchase = new Purchase();
    purchase.setId(100L);
    Collection<LineItem> items = new ArrayList<>();
    LineItem item = new LineItem();
    item.setId(99L);
    Product product = new Product();
    product.setPrice(0.0);
    product.setName("New Product");
    item.setProduct(product);
    items.add(item);
    purchase.setProducts(items);
    List<Purchase> purchases = new ArrayList<>();
    purchases.add(purchase);

    List<PurchasedProduct> products = new ArrayList<>();
    PurchasedProduct purchasedProduct = new PurchasedProduct();
    purchasedProduct.setProductKey(99L);
    purchasedProduct.setQuantity(0);
    purchasedProduct.setProductCost(0.0);
    purchasedProduct.setName("New Product");
    products.add(purchasedProduct);

    UserPurchase userPurchase = new UserPurchase();
    userPurchase.setPurchaseKey(100L);
    userPurchase.setPurchaseDate("");
    userPurchase.setPurchaseCost(0.0);
    userPurchase.setProducts(products);

    List<UserPurchase> expected = new ArrayList<>();
    expected.add(userPurchase);

    when(purchaseRepository.findByUserEmail(anyString())).thenReturn(purchases);
    List<UserPurchase> actual = purchaseServiceImpl.findUserPurchasesByEmail(
        "asalerno@catalyte.io");

    assertThat(actual, samePropertyValuesAs(expected));
  }

  /**
   * Checks if purchase.toString() returns in the correct format
   *
   * @author - Andrew Salerno
   */
  @Test
  public void purchaseToStringReturnsCorrectFormat() {
    Purchase purchase = new Purchase();
    DeliveryAddress deliveryAddress = new DeliveryAddress();
    purchase.setDeliveryAddress(deliveryAddress);
    String actual = purchase.toString();
    String expected =
        "Purchase{" +
            "id=" + null +
            ", deliveryAddress=" + deliveryAddress +
            ", billingAddress=" + new BillingAddress() +
            ", creditCard=" + new CreditCard() +
            '}';
    assertEquals(expected, actual, "Strings do not match");
  }
}