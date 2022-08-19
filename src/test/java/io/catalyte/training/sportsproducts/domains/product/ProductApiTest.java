package io.catalyte.training.sportsproducts.domains.product;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static io.catalyte.training.sportsproducts.constants.Paths.PRODUCTS_PATH;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApiTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductServiceImpl productService;


  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  /**
   * Path for getting all products responds with 200 status
   *
   * @throws Exception
   */
  @Test
  public void getProductsReturns200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH))
        .andExpect(status().isOk());
  }

  /**
   * Check that get request return only active product.
   *
   * @throws Exception
   */
  @Test
  public void getProductReturnsOnlyActiveProducts() throws Exception {
  mockMvc.perform(get(PRODUCTS_PATH))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].active", hasItems(true)));
    }

  /**
   * Path for getting product with given ID responds with 200 status
   *
   * @throws Exception
   */
  @Test
  public void getProductByIdReturnsProductWith200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/1"))
        .andExpect(status().isOk());
  }

  /**
   * Path for getting all types of products in database responds with 200 status
   *
   * @throws Exception
   */
  @Test
  public void getUniqueTypeWith200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/types"))
        .andExpect(status().isOk());
  }

  /**
   * Path for getting all categories of products in database responds with 200 status
   *
   * @throws Exception
   */
  @Test
  public void getUniqueCategoryWith200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/categories"))
        .andExpect(status().isOk());
  }

  /**
   * Get all products from the productRepository and check that they have the required fields
   */
  @Test
  public void findAllReturnsProductsWithCorrectFields() {
    List<Product> products = productRepository.findAll();
    for (Product product : products) {
      assertAll(
          () -> assertThat(product, hasProperty("id")),
          () -> assertThat(product, hasProperty("active")),
          () -> assertThat(product, hasProperty("brand")),
          () -> assertThat(product, hasProperty("category")),
          () -> assertThat(product, hasProperty("demographic")),
          () -> assertThat(product, hasProperty("description")),
          () -> assertThat(product, hasProperty("category")),
          () -> assertThat(product, hasProperty("globalProductCode")),
          () -> assertThat(product, hasProperty("imageSrc")),
          () -> assertThat(product, hasProperty("material")),
          () -> assertThat(product, hasProperty("name")),
          () -> assertThat(product, hasProperty("price")),
          () -> assertThat(product, hasProperty("primaryColorCode")),
          () -> assertThat(product, hasProperty("quantity")),
          () -> assertThat(product, hasProperty("releaseDate")),
          () -> assertThat(product, hasProperty("secondaryColorCode")),
          () -> assertThat(product, hasProperty("styleNumber")),
          () -> assertThat(product, hasProperty("type"))
      );
    }
  }

  /**
   * Get all products from the productRepository and match their field values against the product
   * field values returned by getProductById()
   */
  @Test
  public void getProductByIdMatchesProductsReturnedByFindAll() {
    List<Product> products = productRepository.findAll();
    for (int i = 0; i < products.size(); i++) {
      Product expected = products.get(i);
      Product actual = productService.getProductById((long) (i + 1));
      assertEquals("Products at ID = " + (i + 1) + " do not match", expected, actual);
    }
  }

}
