package io.catalyte.training.sportsproducts.domains.product;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import io.catalyte.training.sportsproducts.data.ProductFactory;
import io.catalyte.training.sportsproducts.exceptions.DataAccessError;
import io.catalyte.training.sportsproducts.exceptions.ResourceNotFound;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ProductServiceImpl.class)
public class ProductServiceImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();
  Product testProduct;
  ProductFactory productFactory;
  @InjectMocks
  private ProductServiceImpl productServiceImpl;
  @Mock
  private ProductRepository productRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    productFactory = new ProductFactory();
    testProduct = productFactory.createRandomProduct();

    when(productRepository.findById(anyLong())).thenReturn(Optional.of(testProduct));
  }

  /**
   * A product is returned when an id is given
   */
  @Test
  public void getProductByIdReturnsProduct() {
    Product actual = productServiceImpl.getProductById(123L);
    System.out.println(actual);
    assertEquals(testProduct, actual);
  }

  /**
   * An error is thrown when an id is given and no product is returned
   */
  @Test
  public void getProductByIdThrowsErrorWhenNotFound() {
    when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
    assertThrows(ResourceNotFound.class, () -> productServiceImpl.getProductById(123L));
  }

  /**
   * Get a product by ID from the productRepository and assert that it has the required fields
   */
  @Test
  public void getProductByIdHasRequiredFields() {
    Product actual = productServiceImpl.getProductById(123L);
    assertAll(() -> assertThat(actual, hasProperty("id")),
        () -> assertThat(actual, hasProperty("active")),
        () -> assertThat(actual, hasProperty("brand")),
        () -> assertThat(actual, hasProperty("category")),
        () -> assertThat(actual, hasProperty("demographic")),
        () -> assertThat(actual, hasProperty("description")),
        () -> assertThat(actual, hasProperty("category")),
        () -> assertThat(actual, hasProperty("globalProductCode")),
        () -> assertThat(actual, hasProperty("imageSrc")),
        () -> assertThat(actual, hasProperty("material")),
        () -> assertThat(actual, hasProperty("name")),
        () -> assertThat(actual, hasProperty("price")),
        () -> assertThat(actual, hasProperty("primaryColorCode")),
        () -> assertThat(actual, hasProperty("quantity")),
        () -> assertThat(actual, hasProperty("releaseDate")),
        () -> assertThat(actual, hasProperty("secondaryColorCode")),
        () -> assertThat(actual, hasProperty("styleNumber")),
        () -> assertThat(actual, hasProperty("type"))
    );
  }

  /**
   * getProducts() data access exception test
   */
  @Test
  public void getProductsReturnsDataAccessExceptionMessage () {
    when(productRepository.findAll()).thenThrow(new DataAccessError(""));
    try {
      Map<String, String> allParams = new HashMap<>();
      productServiceImpl.getProducts(allParams);
    } catch (ServerError e) {
      String actualServerMessage = e.getMessage();
      String expectedServerMessage = "";
      assertEquals(expectedServerMessage, actualServerMessage);
    }
  }

  /**
   * getProductById() data access exception test
   */
  @Test
  public void getProductByIdReturnsDataAccessExceptionMessage () {
    when(productRepository.findById(anyLong())).thenThrow(
        new DataAccessError(""));
    try {
      long anyId = 123L;
      productServiceImpl.getProductById(anyId);
    } catch (ServerError e) {
      String actualServerMessage = e.getMessage();
      String expectedServerMessage = "";
      assertEquals(expectedServerMessage, actualServerMessage);
    }

  }

  /**
   * getUniqueTypes() data access exception test
   */
  @Test
  public void getUniqueTypesReturnsDataAccessExceptionMessage () {
    when(productRepository.findByType()).thenThrow(new DataAccessError(""));
    try {
      productServiceImpl.getUniqueTypes();
    } catch (ServerError e) {
      String actualServerMessage = e.getMessage();
      String expectedServerMessage = "";
      assertEquals(expectedServerMessage, actualServerMessage);
    }
  }

  /**
   * getUniqueCategories() data access exception test
   */
  @Test
  public void getUniqueCategoriesReturnsDataAccessExceptionMessage () {
    when(productRepository.findByCategory()).thenThrow(new DataAccessError(""));
    try {
      productServiceImpl.getUniqueCategories();
    } catch (ServerError e) {
      String actualServerMessage = e.getMessage();
      String expectedServerMessage = "";
      assertEquals(expectedServerMessage, actualServerMessage);
    }
  }

  /**
   * Checks product.toString() returns the correct format
   */
  @Test
  public void productToStringReturnsCorrectFormat () {
    Product product = new Product();
    String actual = product.toString();
    String expected =
        "Product {" + "id='" + null + '\'' + ", name='" + null + '\'' + ", brand='" + null + '\''
            + ", description='" + null + '\'' + ", demographic='" + null + '\'' + ", category='"
            + null + '\'' + ", type='" + null + '\'' + ", releaseDate='" + null + '\'' + ", price='"
            + null + '\'' + ", primaryColorCode='" + null + '\'' + ", quantity='" + null + '\''
            + ", secondaryColorCode='" + null + '\'' + ", styleNumber='" + null + '\''
            + ", globalProductCode='" + null + '\'' + ", imageSrc='" + null + '\'' + ", material='"
            + null + '\'' + ", active='" + null + '\'' + '}';
    assertEquals(expected, actual, "Strings do not match");
  }

  /**
   * Checks that product.hashCode() returns
   */
  @Test
  public void productHashCodeReturnsResult () {
    Product product = new Product();
    int actual = product.hashCode();
    int expected = product.hashCode();
    assertEquals(expected, actual, "Strings do not match");
  }

  /**
   * Checks that product.equals() returns false after passing a Null object
   */
  @Test
  public void equalsReturnsFalseIfProductIsNull () {
    Product actual = new Product();
    Product expected = new Product();
    expected = null;
    assertFalse(actual.equals(expected), "Product is not Null");
  }

  /**
   * Creates a Product with the Product constructor and then sets its id with product.setId()
   * then gets the product id with product.getId() and then compares the values
   */
  @Test
  public void constructProductSetIDGetID () {
    Product product = new Product("name", "description", "demographic",
        "category", "type", "releaseDate");
    long expected = 9999L;
    long actual;
    product.setId(expected);
    actual = product.getId();
    assertEquals(expected, actual);
  }

  /**
   * Creates a Product with the Product constructor, gets its name and compares
   */
  @Test
  public void constructProductAndGetName () {
    Product product = new Product("name", "description", "demographic",
        "category", "type", "releaseDate");
    String expected = "name";
    String actual = "";
    actual = product.getName();
    assertEquals(expected, actual);
    product.getActive();
  }

  /**
   * Checks that product.equals() returns false if Products have the same name
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentName () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setName("name");
    expected.setName("differentName");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same name");
  }

  /**
   * Checks that product.equals() returns false if Products have the same brand
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentBrand () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setBrand("brand");
    expected.setBrand("differentBrand");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same brand");
  }

  /**
   * Checks that product.equals() returns false if Products have the same description
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentDescription () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setDescription("description");
    expected.setDescription("differentDescription");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same description");
  }

  /**
   * Checks that product.equals() returns false if Products have the same demographic
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentDemographic () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setDemographic("demographic");
    expected.setDemographic("differentDemographic");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same demographic");
  }

  /**
   * Checks that product.equals() returns false if Products have the same category
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentCategory () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setCategory("category");
    expected.setCategory("differentCategory");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same category");
  }

  /**
   * Checks that product.equals() returns false if Products have the same type
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentType () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setType("type");
    expected.setType("differentType");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same type");
  }

  /**
   * Checks that product.equals() returns false if Products have the same releaseDate
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentReleaseDate () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setReleaseDate("releaseDate");
    expected.setReleaseDate("differentReleaseDate");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same releaseDate");
  }

  /**
   * Checks that product.equals() returns false if Products have the same price
   */
  @Test

  public void equalsReturnsFalseIfObjectHasDifferentPrice () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setPrice(1.00);
    expected.setPrice(0.00);
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same price");
  }

  /**
   * Checks that product.equals() returns false if Products have the same primaryColorCode
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentPrimaryColorCode () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setPrimaryColorCode("primaryColorCode");
    expected.setPrimaryColorCode("differentPrimaryColorCode");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same primaryColorCode");
  }

  /**
   * Checks that product.equals() returns false if Products have the same quantity
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentQuantity () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setQuantity(1);
    expected.setQuantity(0);
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same quantity");
  }

  /**
   * Checks that product.equals() returns false if Products have the same secondaryColorCode
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentSecondaryColorCode () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setSecondaryColorCode("secondaryColorCode");
    expected.setSecondaryColorCode("differentSecondaryColorCode");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same secondaryColorCode");
  }

  /**
   * Checks that product.equals() returns false if Products have the same styleNumber
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentStyleNumber () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setStyleNumber("styleNumber");
    expected.setStyleNumber("differentStyleNumber");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same styleNumber");
  }

  /**
   * Checks that product.equals() returns false if Products have the same globalProductCode
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentGlobalProductCode () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setGlobalProductCode("globalProductCode");
    expected.setGlobalProductCode("differentGlobalProductCode");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same globalProductCode");
  }

  /**
   * Checks that product.equals() returns false if Products have the same imageSrc
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentImageSrc () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setImageSrc("imageSrc");
    expected.setImageSrc("differentImageSrc");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same imageSrc");
  }

  /**
   * Checks that product.equals() returns false if Products have the same material
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentMaterial () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setMaterial("material");
    expected.setMaterial("differentMaterial");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same material");
  }

  /**
   * Checks that product.equals() returns an active product
   */
  @Test
  public void equalsReturnsTrueIfProductIsActive () {
    Product actual = new Product();
    Product expected = new Product();
    actual.setActive(true);
    expected.setActive(false);
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same active status");
  }
}
