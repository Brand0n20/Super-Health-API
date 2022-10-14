package io.catalyte.training.superhealthapi.domains.product;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import io.catalyte.training.superhealthapi.data.ProductFactory;
import io.catalyte.training.superhealthapi.exceptions.DataAccessError;
import io.catalyte.training.superhealthapi.exceptions.ResourceNotFound;
import io.catalyte.training.superhealthapi.exceptions.ServerError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

/**
 * Unit testing for Product
 */
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
   *
   * @author - Andrew Salerno
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
   *
   * @author - Andrew Salerno
   */
  @Test
  public void getProductsReturnsDataAccessExceptionMessage() {
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
   *
   * @author - Andrew Salerno
   */
  @Test
  public void getProductByIdReturnsDataAccessExceptionMessage() {
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
   * saveProduct() data access exception test
   *
   * @author - Andrew Salerno
   */
  @Test
  public void saveProductReturnsDataAccessExceptionMessage() {
    Product product = new Product();
    when(productRepository.save(product)).thenThrow(
        new DataAccessError(""));
    try {
      productServiceImpl.saveProduct(product);
    } catch (ServerError e) {
      String actualServerMessage = e.getMessage();
      String expectedServerMessage = "";
      assertEquals(expectedServerMessage, actualServerMessage);
    }
  }

  /**
   * getUniqueTypes() data access exception test
   *
   * @author - Andrew Salerno
   */
  @Test
  public void getUniqueTypesReturnsDataAccessExceptionMessage() {
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
   * getUniqueDemographics() data access exception test
   *
   * @author - Andrew Salerno
   */
  @Test
  public void getUniqueDemographicsReturnsDataAccessExceptionMessage() {
    when(productRepository.findByDemographic()).thenThrow(new DataAccessError(""));
    try {
      productServiceImpl.getUniqueDemographics();
    } catch (ServerError e) {
      String actualServerMessage = e.getMessage();
      String expectedServerMessage = "";
      assertEquals(expectedServerMessage, actualServerMessage);
    }
  }

  /**
   * getUniqueCategories() data access exception test
   *
   * @author - Andrew Salerno
   */
  @Test
  public void getUniqueCategoriesReturnsDataAccessExceptionMessage() {
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
   * getUniqueBrands() data access exception test
   *
   * @author - Andrew Salerno
   */
  @Test
  public void getUniqueBrandsReturnsDataAccessExceptionMessage() {
    when(productRepository.findByBrand()).thenThrow(new DataAccessError(""));
    try {
      productServiceImpl.getUniqueBrands();
    } catch (ServerError e) {
      String actualServerMessage = e.getMessage();
      String expectedServerMessage = "";
      assertEquals(expectedServerMessage, actualServerMessage);
    }
  }

  /**
   * getUniqueMaterials() data access exception test
   *
   * @author - Andrew Salerno
   */
  @Test
  public void getUniqueMaterialsReturnsDataAccessExceptionMessage() {
    when(productRepository.findByMaterial()).thenThrow(new DataAccessError(""));
    try {
      productServiceImpl.getUniqueMaterials();
    } catch (ServerError e) {
      String actualServerMessage = e.getMessage();
      String expectedServerMessage = "";
      assertEquals(expectedServerMessage, actualServerMessage);
    }
  }

  /**
   * getUniqueColors() data access exception test
   *
   * @author - Andrew Salerno
   */
  @Test
  public void getUniqueColorsReturnsDataAccessExceptionMessage() {
    when(productRepository.findByPrimaryColor()).thenThrow(new DataAccessError(""));
    try {
      productServiceImpl.getUniqueColors();
    } catch (ServerError e) {
      String actualServerMessage = e.getMessage();
      String expectedServerMessage = "";
      assertEquals(expectedServerMessage, actualServerMessage);
    }
  }

  /**
   * Checks product.toString() returns the correct format
   *
   * @author - Andrew Salerno
   */
  @Test
  public void productToStringReturnsCorrectFormat() {
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
   *
   * @author - Andrew Salerno
   */
  @Test
  public void productHashCodeReturnsResult() {
    Product product = new Product();
    int actual = product.hashCode();
    int expected = product.hashCode();
    assertEquals(expected, actual, "Strings do not match");
  }

  /**
   * Checks that product.equals() returns false after passing a Null object
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfProductIsNull() {
    Product actual = new Product();
    Product expected = new Product();
    expected = null;
    assertFalse(actual.equals(expected), "Product is not Null");
  }

  /**
   * Creates a Product with the Product constructor and then sets its id with product.setId() then
   * gets the product id with product.getId() and then compares the values
   *
   * @author - Andrew Salerno
   */
  @Test
  public void constructProductSetIDGetID() {
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
   *
   * @author - Andrew Salerno
   */
  @Test
  public void constructProductAndGetName() {
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
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentName() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setName("name");
    expected.setName("differentName");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same name");
  }

  /**
   * Checks that product.equals() returns false if Products have the same brand
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentBrand() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setBrand("brand");
    expected.setBrand("differentBrand");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same brand");
  }

  /**
   * Checks that product.equals() returns false if Products have the same description
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentDescription() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setDescription("description");
    expected.setDescription("differentDescription");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same description");
  }

  /**
   * Checks that product.equals() returns false if Products have the same demographic
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentDemographic() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setDemographic("demographic");
    expected.setDemographic("differentDemographic");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same demographic");
  }

  /**
   * Checks that product.equals() returns false if Products have the same category
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentCategory() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setCategory("category");
    expected.setCategory("differentCategory");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same category");
  }

  /**
   * Checks that product.equals() returns false if Products have the same type
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentType() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setType("type");
    expected.setType("differentType");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same type");
  }

  /**
   * Checks that product.equals() returns false if Products have the same releaseDate
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentReleaseDate() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setReleaseDate("releaseDate");
    expected.setReleaseDate("differentReleaseDate");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same releaseDate");
  }

  /**
   * Checks that product.equals() returns false if Products have the same price
   *
   * @author - Andrew Salerno
   */
  @Test

  public void equalsReturnsFalseIfObjectHasDifferentPrice() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setPrice(1.00);
    expected.setPrice(0.00);
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same price");
  }

  /**
   * Checks that product.equals() returns false if Products have the same primaryColorCode
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentPrimaryColorCode() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setPrimaryColorCode("primaryColorCode");
    expected.setPrimaryColorCode("differentPrimaryColorCode");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same primaryColorCode");
  }

  /**
   * Checks that product.equals() returns false if Products have the same quantity
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentQuantity() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setQuantity(1);
    expected.setQuantity(0);
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same quantity");
  }

  /**
   * Checks that product.equals() returns false if Products have the same secondaryColorCode
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentSecondaryColorCode() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setSecondaryColorCode("secondaryColorCode");
    expected.setSecondaryColorCode("differentSecondaryColorCode");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same secondaryColorCode");
  }

  /**
   * Checks that product.equals() returns false if Products have the same styleNumber
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentStyleNumber() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setStyleNumber("styleNumber");
    expected.setStyleNumber("differentStyleNumber");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same styleNumber");
  }

  /**
   * Checks that product.equals() returns false if Products have the same globalProductCode
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentGlobalProductCode() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setGlobalProductCode("globalProductCode");
    expected.setGlobalProductCode("differentGlobalProductCode");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same globalProductCode");
  }

  /**
   * Checks that product.equals() returns false if Products have the same imageSrc
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentImageSrc() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setImageSrc("imageSrc");
    expected.setImageSrc("differentImageSrc");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same imageSrc");
  }

  /**
   * Checks that product.equals() returns false if Products have the same material
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsFalseIfObjectHasDifferentMaterial() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setMaterial("material");
    expected.setMaterial("differentMaterial");
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same material");
  }

  /**
   * Checks that product.equals() returns an active product
   *
   * @author - Andrew Salerno
   */
  @Test
  public void equalsReturnsTrueIfProductIsActive() {
    Product actual = new Product();
    Product expected = new Product();
    actual.setActive(true);
    expected.setActive(false);
    boolean result = actual.equals(expected);
    assertFalse(result, "Products have the same active status");
  }

  /**
   * Checks that an empty list is returned when empty parameters are passed to getProducts()
   *
   * @author - AndrewSalerno
   */
  @Test
  public void getProductsReturnsEmptyWithEmptyParams() {
    Map<String, String> allParams = new HashMap<>();
    allParams.put("", "");
    List<Product> actual = productServiceImpl.getProducts(allParams);
    List<Product> expected = new ArrayList<>();
    assertEquals(expected, actual);
  }

  /**
   * Checks that getProducts() returns and empty list when invalid parameters are passed
   *
   * @author - AndrewSalerno
   */
  @Test
  public void getProductsReturnsEmptyOnInvalidParams() {
    Map<String, String> allParams = new HashMap<>();
    allParams.put("NotValidKey", "NotValidValue");
    List<Product> actual = productServiceImpl.getProducts(allParams);
    List<Product> expected = new ArrayList<>();
    assertEquals(expected, actual);
  }

  /**
   * Checks that getProducts() returns a list of products when valid parameters are passed
   *
   * @author - Andrew Salerno
   */
  @Test
  public void getProductsReturnsProductsOnValidParams() {
    Map<String, String> allParams = new HashMap<>();
    allParams.put("price", "100.00");
    List<Product> actual = productServiceImpl.getProducts(allParams);
    List<Product> expected = new ArrayList<>();
    assertEquals(expected, actual);
  }


  @Test
  public void saveProductReturnsNewProduct() {
    Product expected = new Product();
    expected.setId(999L);

    Product actual = productServiceImpl.saveProduct(expected);
    assertEquals(expected, actual);
  }
}
