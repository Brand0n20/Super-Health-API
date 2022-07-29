package io.catalyte.training.sportsproducts.domains.product;

import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import io.catalyte.training.sportsproducts.data.ProductFactory;
import io.catalyte.training.sportsproducts.exceptions.ResourceNotFound;
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

  @InjectMocks
  private ProductServiceImpl productServiceImpl;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private ProductRepository productRepository;

  Product testProduct;

  ProductFactory productFactory;

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
  public void getProductByIdReturnsProduct () {
    Product actual = productServiceImpl.getProductById(123L);
    assertEquals(testProduct, actual);
  }

  /**
   * An error is thrown when an id is given and no product is returned
   */
  @Test
  public void getProductByIdThrowsErrorWhenNotFound () {
    when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
    assertThrows(ResourceNotFound.class, () -> productServiceImpl.getProductById(123L));
  }

  /**
   * Get a product by ID from the productRepository and assert that it has the required fields
   */
  @Test
  public void getProductByIdHasRequiredFields () {
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
}
