package io.catalyte.training.superhealthapi.responseObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * A UserPurchase response object
 *
 * @author - Andrew Salerno
 */
public class UserPurchase {

  /**
   * The key used to render a UserPurchase in the UI
   *
   * @author - Andrew Salerno
   */
  private long purchaseKey;

  /**
   * The purchase Date
   *
   * @author - Andrew Salerno
   */
  String purchaseDate;

  /**
   * The list of PurchasedProduct responseObjects
   *
   * @author - Andrew Salerno
   */
  List<PurchasedProduct> products;

  /**
   * The total cost of the purchase
   *
   * @author - Andrew Salerno
   */
  double purchaseCost;

  /**
   * No argument constructor for a new User Purchase Response
   *
   * @author - Andrew Salerno
   */
  public UserPurchase() {
    this.purchaseDate = "";
    this.products = new ArrayList<>();
  }

  /**
   * Gets the key value of a UserPurchase
   *
   * @return The UserPurchase key value
   * @author - Andrew Salerno
   */
  public long getPurchaseKey() {
    return purchaseKey;
  }

  /**
   * Sets the UserPurchase key value
   *
   * @param purchaseKey - The purchase key
   * @author - Andrew Salerno
   */
  public void setPurchaseKey(long purchaseKey) {
    this.purchaseKey = purchaseKey;
  }

  /**
   * Gets the Total Cost of a Purchase
   *
   * @return - The cost of the purchase
   * @author - Andrew Salerno
   */
  public double getPurchaseCost() {
    return purchaseCost;
  }

  /**
   * Sets the Total Cost of a Purchase
   *
   * @param totalPrice
   * @author - Andrew Salerno
   */
  public void setPurchaseCost(double totalPrice) {
    this.purchaseCost = totalPrice;
  }

  /**
   * Gets the date of the Purchase
   *
   * @return - The purchase date
   * @author - Andrew Salerno
   */
  public String getPurchaseDate() {
    return purchaseDate;
  }

  /**
   * Sets the date of a Purchase
   *
   * @param purchaseDate - The purchase date
   * @author - Andrew Salerno
   */
  public void setPurchaseDate(String purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  /**
   * Gets the product response objects in a UserPurchase response
   *
   * @return
   * @author - Andrew Salerno
   */
  public List<PurchasedProduct> getProducts() {
    return products;
  }

  /**
   * Sets the PurchasedProduct response objects in a UserPurchase response
   *
   * @param products
   * @author - Andrew Salerno
   */
  public void setProducts(
      List<PurchasedProduct> products) {
    this.products = products;
  }
}
