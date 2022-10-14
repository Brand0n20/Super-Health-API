package io.catalyte.training.superhealthapi.domains.product;

import static io.catalyte.training.superhealthapi.constants.Paths.PRODUCTS_PATH;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApiTest {

  @Autowired
  ProductRepository productRepository;
  @Autowired
  ProductServiceImpl productService;
  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
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
   * Path for getting all brands of products in database responds with 200 status
   *
   * @throws Exception
   */
  @Test
  public void getUniqueBrandWith200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/brands"))
        .andExpect(status().isOk());
  }

  /**
   * Path for getting all demographics of products in database responds with 200 status
   *
   * @throws Exception
   */
  @Test
  public void getUniqueDemographicWith200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/demographics"))
        .andExpect(status().isOk());
  }

  /**
   * Path for getting all colors of products in database responds with 200 status
   *
   * @throws Exception
   */
  @Test
  public void getUniqueColorWith200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/colors"))
        .andExpect(status().isOk());
  }

  /**
   * Path for getting all colors of products in database responds with 200 status
   *
   * @throws Exception
   */
  @Test
  public void getUniqueMaterialWith200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/materials"))
        .andExpect(status().isOk());
  }

  /**
   * Delete product from the productRepository and return 200 OK
   */

  @Test
  public void deleteProductWith200() throws Exception {
    Product product = productRepository.findById(24L).orElse(null);

    MockHttpServletRequestBuilder builder = delete(PRODUCTS_PATH).contentType(
            MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .content(asJsonString(product));

    mockMvc.perform(builder).andExpect(status().isOk());
  }

  /**
   * Update product from the productRepository and return 200 OK
   */

  @Test
  public void updateProductWith200() throws Exception {
    Product product = productRepository.findById(24L).orElse(null);

    MockHttpServletRequestBuilder builder = put(PRODUCTS_PATH).contentType(
            MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .content(asJsonString(product));

    mockMvc.perform(builder).andExpect(status().isOk());
  }

  /**
   * Get all products from the productRepository and check that they have the required fields
   *
   * @author - Andrew Salerno
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
   *
   * @author - Andrew Salerno
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

  /**
   * @throws Exception - Data access exception
   */
  @Test
  public void saveProductReturnsProduct() throws Exception {
    Product product = new Product();
    mockMvc.perform(
            post(PRODUCTS_PATH).contentType(MediaType.APPLICATION_JSON).content(asJsonString(product)))
        .andExpect(status().isCreated());
  }

}
