package io.catalyte.training.sportsproducts.domains.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class ProductFilterTest {

  ProductFilter productFilter;

  @Before
  public void setup() {
    productFilter = new ProductFilter();
  }

  @Test
  public void createsSingleQuery() {
    Map<String, String> params = Map.of("brand", "Nike");

    productFilter.createUniqueParams(params);

    String expected = "SELECT p FROM Product p WHERE (p.brand IN ('Nike'))";

    assertEquals(expected, productFilter.createFilterQuery());
  }

  @Test
  public void createsMultiValueQuery() {
    Map<String, String> params = Map.of("brand", "Nike,New-Balance,the-north-face");

    productFilter.createUniqueParams(params);

    String expected = "SELECT p FROM Product p WHERE (p.brand IN ('Nike', 'New Balance', 'The North Face'))";

    assertEquals(expected, productFilter.createFilterQuery());
  }

  @Test
  public void createsColorQuery() {
    Map<String, String> params = Map.of("color", "e15258");

    productFilter.createUniqueParams(params);

    String expected = "SELECT p FROM Product p WHERE ((p.primaryColorCode IN ('#e15258') OR p.secondaryColorCode IN ('#e15258')))";

    assertEquals(expected, productFilter.createFilterQuery());
  }

  @Test
  public void createsPriceQuery() {
    Map<String, String> params = Map.of("price", "$20.55");

    productFilter.createUniqueParams(params);

    String expected = "SELECT p FROM Product p WHERE (p.price IN (20.55))";

    assertEquals(expected, productFilter.createFilterQuery());
  }

  @Test
  public void createsMinPriceQuery() {
    Map<String, String> params = Map.of("min-price", "$20.55");

    productFilter.createUniqueParams(params);

    String expected = "SELECT p FROM Product p WHERE ((p.price >= 20.55))";

    assertEquals(expected, productFilter.createFilterQuery());
  }

  @Test
  public void createsMaxPriceQuery() {
    Map<String, String> params = Map.of("max-price", "$20.55");

    productFilter.createUniqueParams(params);

    String expected = "SELECT p FROM Product p WHERE ((p.price <= 20.55))";

    assertEquals(expected, productFilter.createFilterQuery());
  }

  @Test
  public void returnsNullWithNoUniqueParamCreated() {
    assertNull(productFilter.createFilterQuery());
  }

  @Test
  public void returnsNullWithEmptyParams() {
    Map<String, String> params = Map.of();

    productFilter.createUniqueParams(params);
    assertNull(productFilter.createFilterQuery());
  }

}
