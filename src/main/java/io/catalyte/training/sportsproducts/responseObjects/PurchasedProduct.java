package io.catalyte.training.sportsproducts.responseObjects;

/**
 * A product response object
 */
public class PurchasedProduct {

  private long productKey;

  /**
   * The name of the PurchasedProduct
   */
  String name;

  /**
   * The total cost of the PurchasedProduct - Individual product price X quantity ordered
   */
  double productCost;

  /**
   * The quantity of the PurchasedProduct
   */
  int quantity;

  /**
   * No argument constructor for a purchased product response
   */
  public PurchasedProduct () {
    this.name = new String();
  }

  public long getProductKey () {
    return productKey;
  }
  public void setProductKey ( long productKey ) {
    this.productKey = productKey;
  }

  /**
   * Gets the name of the product in a product response
   * @return - The name of the product
   */
  public String getName () {
    return name;
  }

  /**
   * Sets the name of a product in a product response
   * @param name - The name to set of the product
   */
  public void setName ( String name ) {
    this.name = name;
  }

  /**
   * Gets the total cost of the product - Individual product price X quantity ordered
   * @return - The total cost of the product
   */
  public double getProductCost () {
    return productCost;
  }

  /**
   * Sets the total cost of a product - Individual product price X quantity ordered
   * @param price - The total cost of a product
   */
  public void setProductCost ( double price ) {
    this.productCost = price;
  }

  /**
   * Gets the quantity in a product response object
   * @return - The quantity of a product ordered
   */
  public int getQuantity () {
    return quantity;
  }

  /**
   * Sets the quantity of a product ordered
   * @param quantity - The quantity of a product ordered
   */
  public void setQuantity ( int quantity ) {
    this.quantity = quantity;
  }
}
