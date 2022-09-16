package io.catalyte.training.sportsproducts.responseObjects;

/**
 * A product response object
 *
 * @author - Andrew Salerno
 */
public class PurchasedProduct {

  /**
   * The product key used to render map the purchase response in the UI
   */
  private long productKey;

  /**
   * The name of the PurchasedProduct
   *
   * @author - Andrew Salerno
   */
  String name;

  /**
   * The total cost of the PurchasedProduct - Individual product price X quantity ordered
   *
   * @author - Andrew Salerno
   */
  double productCost;

  /**
   * The quantity of the PurchasedProduct
   *
   * @author - Andrew Salerno
   */
  int quantity;

  /**
   * No argument constructor for a purchased product response
   *
   * @author - Andrew Salerno
   */
  public PurchasedProduct() {
    this.name = "";
  }

  /**
   * Gets the product key of a Purchase
   *
   * @return - The product key
   * @author - Andrew Salerno
   */
  public long getProductKey() {
    return productKey;
  }

  /**
   * Sets the product key of a purchase
   *
   * @param productKey - The product key of a purchae
   * @author - Andrew Salerno
   */
  public void setProductKey(long productKey) {
    this.productKey = productKey;
  }

  /**
   * Gets the name of the product in a product response
   *
   * @return - The name of the product
   * @author - Andrew Salerno
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of a product in a product response
   *
   * @param name - The name to set of the product
   * @author - Andrew Salerno
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the total cost of the product - Individual product price X quantity ordered
   *
   * @return - The total cost of the product
   * @author - Andrew Salerno
   */
  public double getProductCost() {
    return productCost;
  }

  /**
   * Sets the total cost of a product - Individual product price X quantity ordered
   *
   * @param price - The total cost of a product
   * @author - Andrew Salerno
   */
  public void setProductCost(double price) {
    this.productCost = price;
  }

  /**
   * Gets the quantity in a product response object
   *
   * @return - The quantity of a product ordered
   * @author - Andrew Salerno
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of a product ordered
   *
   * @param quantity - The quantity of a product ordered
   * @author - Andrew Salerno
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
