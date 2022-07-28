package io.catalyte.training.sportsproducts.domains.product;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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

  @Test
  public void getProductByIdReturnsProduct() {
    Product actual = productServiceImpl.getProductById(123L);
    assertEquals(testProduct, actual);
  }

  @Test
  public void getProductByIdThrowsErrorWhenNotFound() {
    when(productRepository.findById(anyLong())).thenReturn(Optional.empty());
    assertThrows(ResourceNotFound.class, () -> productServiceImpl.getProductById(123L));
  }

  @Test
  public void getProductsByIdReturnsNonNullFields() {
    Product actual = productServiceImpl.getProductById(123L);
    assertAll(
        () -> assertNotEquals(actual.getName(), null),
        () -> assertNotEquals(actual.getDescription(), null),
        () -> assertNotEquals(actual.getDemographic(), null),
        () -> assertNotEquals(actual.getCategory(), null),
        () -> assertNotEquals(actual.getType(), null),
        () -> assertNotEquals(actual.getReleaseDate(), null),
        () -> assertNotEquals(actual.getPrimaryColorCode(), null),
        () -> assertNotEquals(actual.getSecondaryColorCode(), null),
        () -> assertNotEquals(actual.getGlobalProductCode(), null),
        () -> assertNotEquals(actual.getActive(), null)
    );
  }

  @Test
  public void getProductsByIdReturnsStrings() {
    Product actual = productServiceImpl.getProductById(123L);
    assertAll(() -> assertNotNull(actual.getName()),
        () -> assertNotNull(actual.getDemographic()),
        () -> assertNotNull(actual.getCategory()),
        () -> assertNotNull(actual.getType()),
        () -> assertNotNull(actual.getReleaseDate()),
        () -> assertNotNull(actual.getPrimaryColorCode()),
        () -> assertNotNull(actual.getSecondaryColorCode()),
        () -> assertNotNull(actual.getGlobalProductCode()));
  }

  @Test
  public void productsActivePropertyIsBoolean() {
    Product actual = productServiceImpl.getProductById(123L);
    assertNotNull(actual.getActive());
  }
}
