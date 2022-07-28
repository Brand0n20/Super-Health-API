package io.catalyte.training.sportsproducts.domains.product;

import static io.catalyte.training.sportsproducts.constants.Paths.PRODUCTS_PATH;
import static org.hamcrest.Matchers.hasProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.apache.http.annotation.Contract;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApiTest {

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductServiceImpl productService;

  private MockMvc mockMvc;


  @Before
  public void setUp () {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void getProductsReturns200 () throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH))
        .andExpect(status().isOk());
  }

  @Test
  public void getProductByIdReturnsProductWith200 () throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/1"))
        .andExpect(status().isOk());

  }

<<<<<<< HEAD
  @Test
  public void getUniqueTypeWith200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/types"))
        .andExpect(status().isOk());
  }

  @Test
  public void getUniqueCategoryWith200() throws Exception {
    mockMvc.perform(get(PRODUCTS_PATH + "/categories"))
        .andExpect(status().isOk());
  }
=======
  /**
   * Get all products from the productRepository and check that they have the required fields
   */
  @Test
  public void findAllReturnsProductsWithCorrectFields () {
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
  public void getProductByIdMatchesProductsReturnedByFindAll () {
    List<Product> products = productRepository.findAll();
    for (int i = 0; i < products.size(); i++) {
      Product expected = products.get(i);
      Product actual = productService.getProductById((long) (i + 1));
      assertEquals("Products at ID = " + (i+1) + " do not match", expected, actual);
    }
  }

  //EndOfFile
>>>>>>> 3918a3456b7682511849c2371391619764841438
}
