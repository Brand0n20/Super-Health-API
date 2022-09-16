package io.catalyte.training.sportsproducts.domains.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.catalyte.training.sportsproducts.domains.product.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit testing for LineItem
 *
 * @author - Andrew Salerno
 */
@RunWith(MockitoJUnitRunner.class)
public class LineItemTest {

  /**
   * A test purchase
   */
  Purchase purchase;

  /**
   * A test product
   */
  Product product;

  /**
   * A test lineItem
   */
  LineItem actualItem;

  /**
   * Creates a new LineItem using the constructor method
   *
   * @author - Andrew Salerno
   */
  @Before
  public void setup() {
    purchase = new Purchase();
    product = new Product();
    actualItem = new LineItem(1L, purchase, product, 123);
  }

  /**
   * Sets the line item quantity gets the line item quantity and compares
   *
   * @author - Andrew Salerno
   */
  @Test
  public void lineItemGetQuantityReturnsQuantity() {
    int expected = 1000;
    actualItem.setQuantity(expected);
    int actual = actualItem.getQuantity();
    assertEquals(expected, actual);
  }

  /**
   * Sets the line items purchase then gets the line item purchase and compares
   *
   * @author - Andrew Salerno
   */
  @Test
  public void lineItemGetPurchaseReturnsPurchase() {
    Purchase expected = new Purchase();
    LineItem item = new LineItem();
    item.setPurchase(expected);

    Purchase actual = item.getPurchase();
    assertEquals(expected, actual);
  }

  /**
   * lineItem.equals() returns true when item is the same item
   *
   * @author - Andrew Salerno
   */
  @Test
  public void lineItemEqualsReturnsTrueOnSameItem() {
    assertTrue(actualItem.equals(actualItem));
  }

  /**
   * Checks that lineItem.equals() retruns false when passing a null item
   *
   * @author - Andrew Salerno
   */
  @Test
  public void lineItemEqualsReturnsFalseOnNullItem() {
    LineItem nullItem = null;
    assertNotEquals(actualItem, nullItem);
  }

  /**
   * lineItem.equals() returns false when quantity is different
   *
   * @author - Andrew Salerno
   */
  @Test
  public void lineItemEqualsReturnsFalseOnDifferentQuantity() {
    LineItem expected = new LineItem();
    expected.setQuantity(12);

    assertFalse(actualItem.equals(expected));
  }

  /**
   * Checks that lineItem.equals() returns false on different purchases
   *
   * @author - Andrew Salerno
   */
  @Test
  public void lineItemEqualsReturnsFalseOnDifferentPurchase() {
    LineItem expected = new LineItem();
    expected.setQuantity(123);
    assertFalse(actualItem.equals(expected));
  }

  /**
   * Checks that lineItem.equals() returns false on different products
   *
   * @author - Andrew Salerno
   */
  @Test
  public void lineItemEqualsReturnsFalseOnDifferentProduct() {
    LineItem expected = new LineItem();
    expected.setQuantity(123);
    expected.setPurchase(purchase);
    assertFalse(actualItem.equals(expected));
  }

  /**
   * Checks that lineItem.hashCode() returns a result
   *
   * @author - Andrew Salerno
   */
  @Test
  public void lineItemHashCodeReturnsResult() {
    int result = actualItem.hashCode();
    assertNotNull(result);
  }

  /**
   * Checks that lineItem.toString() returns the correct format
   *
   * @author - Andrew Salerno
   */
  @Test
  public void lineItemToStringReturnsCorrectFormat() {
    LineItem item = new LineItem();
    String actual = item.toString();
    String expected =
        "LineItem{" +
            "id=" + null +
            ", purchase=" + null +
            ", product=" + null +
            ", quantity=" + 0 +
            '}';
    assertEquals(expected, actual, "Strings do not match");
  }
}