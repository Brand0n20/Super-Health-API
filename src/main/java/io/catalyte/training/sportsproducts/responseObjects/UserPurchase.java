package io.catalyte.training.sportsproducts.responseObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * A UserPurchase response object
 */
public class UserPurchase {

  private long purchaseKey;

  /**
   * The purchase Date
   */
  String purchaseDate;

  /**
   * The list of PurchasedProduct responseObjects
   */
  List<PurchasedProduct> products;

  /**
   * The total cost of the purchase
   */
  double purchaseCost;

  /**
   * No argument constructor for a new User Purchase Response
   */
  public UserPurchase () {
    this.purchaseDate = new String();
    this.products = new ArrayList<>();
  }

  public long getPurchaseKey () {
    return purchaseKey;
  }
  public void setPurchaseKey ( long purchaseKey ) {
    this.purchaseKey = purchaseKey;
  }

  /**
   * Gets the Total Cost of a Purchase
   * @return - The cost of the purchase
   */
  public double getPurchaseCost () {
    return purchaseCost;
  }

  /**
   * Sets the Total Cost of a Purchase
   * @param totalPrice
   */
  public void setPurchaseCost ( double totalPrice ) {
    this.purchaseCost = totalPrice;
  }

  /**
   * Gets the date of the Purchase
   * @return - The purchase date
   */
  public String getPurchaseDate () {
    return purchaseDate;
  }

  /**
   * Sets the date of a Purchase
   * @param purchaseDate - The purchase date
   */
  public void setPurchaseDate ( String purchaseDate ) {
    this.purchaseDate = purchaseDate;
  }

  /**
   * Gets the product response objects in a UserPurchase response
   * @return
   */
  public List<PurchasedProduct> getProducts () {
    return products;
  }

  /**
   * Sets the PurchasedProduct response objects in a UserPurchase response
   * @param products
   */
  public void setProducts (
      List<PurchasedProduct> products ) {
    this.products = products;
  }
}
