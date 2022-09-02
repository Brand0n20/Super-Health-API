package io.catalyte.training.sportsproducts.domains.product;

import static java.util.Map.entry;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

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

    String expected = "SELECT p FROM Product p WHERE" +
        " (p.brand IN ('Nike'))";

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

    String expected = "SELECT p FROM Product p WHERE ((p.primaryColorCode IN ('#e15258') OR " +
        "p.secondaryColorCode IN ('#e15258')))";

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

  @Disabled
  public void createsMultiKeyQuery() {
    Map<String, String> params = Map.ofEntries(
        entry("brand", "Nike"),
        entry("category", "basketball"),
        entry("color", "e15258"),
        entry("material", "Silk"),
        entry("price", "$20.55"),
        entry("max-price", "$400"),
        entry("min-price", "$300"));

    productFilter.createUniqueParams(params);

    String expected = "SELECT p FROM Product p WHERE (p.material IN ('Silk') AND "
        + "(p.primaryColorCode IN ('#e15258') OR p.secondaryColorCode IN ('#e15258')) AND "
        + "p.price IN (20.55) AND (p.price <= 400) AND (p.price >= 300) AND p.category IN ('Basketball') AND "
        + "p.brand IN ('Nike'))";

    assertEquals(expected, productFilter.createFilterQuery());
  }

  @Disabled
  public void createsMultiKeyMultiValueQueryTest() {
    Map<String, String> params = Map.ofEntries(
        entry("brand", "Nike, Adidas"),
        entry("category", "basketball, Football"),
        entry("material", "Silk, Tungsten"),
        entry("demographic", "Men, Women"),
        entry("price", "$20.55, 50.00"),
        entry("max-price", "$400"),
        entry("min-price", "$300"),
        entry("color", "e15258"));

    productFilter.createUniqueParams(params);

    String expected = "SELECT p FROM Product p WHERE ((p.primaryColorCode IN ('#e15258') OR" +
        " p.secondaryColorCode IN ('#e15258')) AND p.material IN ('Silk', 'Tungsten') AND " +
        "p.price IN (50.00, 20.55) AND (p.price <= 400) AND (p.price >= 300) AND " +
        "p.category IN ('Basketball', 'Football') AND p.brand IN ('Nike', 'Adidas') " +
        "AND p.demographic IN ('Women', 'Men'))";

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
