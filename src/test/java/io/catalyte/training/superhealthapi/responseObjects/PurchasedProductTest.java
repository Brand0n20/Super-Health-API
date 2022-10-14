package io.catalyte.training.superhealthapi.responseObjects;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit testing for the PurchasedProduct response object class
 */
@RunWith(MockitoJUnitRunner.class)
public class PurchasedProductTest {

  /**
   * A mock PurchasedProduct response object
   */
  @Mock
  private PurchasedProduct purchasedProduct;

  /**
   * Sets the purchased product key then gets the purchased product key and compares
   *
   * @author - Andrew Salerno
   */
  @Test
  public void setAndGetPurchasedProductProductKey() {
    purchasedProduct = new PurchasedProduct();
    long expected = 0;
    long actual = purchasedProduct.getProductKey();
    assertEquals(expected, actual);

    expected = 1000;
    purchasedProduct.setProductKey(expected);
    actual = purchasedProduct.getProductKey();
    assertEquals(expected, actual);
  }

  /**
   * Sets the purchased product name then gets the purchased product name and compares
   *
   * @author - Andrew Salerno
   */
  @Test
  public void setAndGetPurchasedProductName() {
    purchasedProduct = new PurchasedProduct();
    String expected = "";
    String actual = purchasedProduct.getName();
    assertEquals(expected, actual);

    expected = "Product Name";
    purchasedProduct.setName(expected);
    actual = purchasedProduct.getName();
    assertEquals(expected, actual);
  }

  /**
   * Sets the purchased product cost then gets the purchased product cost and compares
   *
   * @author - Andrew Salerno
   */
  @Test
  public void setAndGetPurchasedProductCost() {
    purchasedProduct = new PurchasedProduct();
    double expected = 0.0;
    double actual = purchasedProduct.getProductCost();
    assertEquals(expected, actual, 0.01);

    expected = 100.01;
    purchasedProduct.setProductCost(expected);
    actual = purchasedProduct.getProductCost();
    assertEquals(expected, actual, 0.01);
  }

  /**
   * Sets the purchased product quantity then gets the purchased product quantity and compares
   *
   * @author - Andrew Salerno
   */
  @Test
  public void setAndGetPurchasedProductQuantity() {
    purchasedProduct = new PurchasedProduct();
    int expected = 0;
    int actual = purchasedProduct.getQuantity();
    assertEquals(expected, actual);

    expected = 555;
    purchasedProduct.setQuantity(expected);
    actual = purchasedProduct.getQuantity();
    assertEquals(expected, actual);
  }
}
