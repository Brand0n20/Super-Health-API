package io.catalyte.training.sportsproducts.responseObjects;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Unit testing for the UserPurchase response object class
 *
 * @author - Andrew Salerno
 */
@RunWith(MockitoJUnitRunner.class)
public class UserPurchaseTest {

  /**
   * A mock UserPurchase response object
   *
   * @author - Andrew Salerno
   */
  @Mock
  UserPurchase userPurchase;

  /**
   * A mock PurcahsedProduct response object
   *
   * @author - Andrew Salerno
   */
  @Mock
  PurchasedProduct purchasedProduct;

  /**
   * Sets the purchased product key then gets the purchased product key and compares
   *
   * @author - Andrew Salerno
   */
  @Test
  public void setAndGetUserPurchasePurchaseKey() {
    userPurchase = new UserPurchase();
    long expected = 0;
    long actual = userPurchase.getPurchaseKey();
    assertEquals(expected, actual);
    expected = 1000;
    userPurchase.setPurchaseKey(expected);
    actual = userPurchase.getPurchaseKey();
    assertEquals(expected, actual);
  }

  /**
   * Sets the UserPurchase date then gets the UserPurchase date and compares
   *
   * @author - Andrew Salerno
   */
  @Test
  public void setAndGetUserPurchasePurchaseDate() {
    userPurchase = new UserPurchase();
    String expected = "";
    String actual = userPurchase.getPurchaseDate();
    assertEquals(expected, actual);

    expected = "11/22/2022";
    userPurchase.setPurchaseDate(expected);
    actual = userPurchase.getPurchaseDate();
    assertEquals(expected, actual);
  }

  /**
   * Sets the UserPurchase cost then gets the UserPurchase cost and compares
   *
   * @author - Andrew Salerno
   */
  @Test
  public void setAndGetPurchasedProductCost() {
    userPurchase = new UserPurchase();
    double expected = 0.0;
    double actual = userPurchase.getPurchaseCost();
    assertEquals(expected, actual, 0.01);

    expected = 100.01;
    userPurchase.setPurchaseCost(expected);
    actual = userPurchase.getPurchaseCost();
    assertEquals(expected, actual, 0.01);
  }

  /**
   * Sets the UserPurchase products then gets the UserPurchase products and compares
   *
   * @author - Andrew Salerno
   */
  @Test
  public void setAndGetPurchasedProducts() {
    userPurchase = new UserPurchase();
    List<PurchasedProduct> expected = new ArrayList<>();
    List<PurchasedProduct> actual = userPurchase.getProducts();
    assertEquals(expected, actual);

    PurchasedProduct product = new PurchasedProduct();
    expected.add(product);
    userPurchase.setProducts(expected);
    actual = userPurchase.getProducts();
    assertEquals(expected, actual);
  }
}