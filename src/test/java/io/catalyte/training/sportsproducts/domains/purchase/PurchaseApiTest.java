package io.catalyte.training.sportsproducts.domains.purchase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static io.catalyte.training.sportsproducts.constants.Paths.PURCHASES_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PurchaseApiTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  /**
   * Purchases with valid credit card can be added into database
   *
   * @throws Exception
   */
  @Test
  public void savePurchaseReturn201StatusCode() throws Exception {
    CreditCard example = new CreditCard("1234567890123456", "234", "10/22", "Bob Ross");
    Purchase purchaseExample = new Purchase();
    purchaseExample.setCreditCard(example);

    this.mockMvc.perform(
        post(PURCHASES_PATH).contentType(MediaType.APPLICATION_JSON).content(
            asJsonString(purchaseExample))).andExpect(status().isCreated());

  }

  /**
   * Mapper to parse object into string for mockMvc
   *
   * @param obj - obj to parse
   * @return
   */
  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   *Path for getting purchases with no email specified responds with 404 status
   *
   * @throws Exception
   */
  @Test
  public void getPurchasesWithoutEmailReturns404() throws Exception {
    mockMvc.perform(get(PURCHASES_PATH)).andExpect(status().isNotFound());
  }

  /**
   * Path for getting purchases by email with matches in database responds with 200 status
   *
   * @throws Exception
   */
  @Test
  public void getPurchasesByEmailReturnsPurchasesWith200() throws Exception {
    mockMvc.perform(get(PURCHASES_PATH + "/email/bob@ross.com")).andExpect(status().isOk());
  }

  /**
   * Path for getting purchases by email with no matches in database responds with 200 status
   *
   * @throws Exception
   */
  @Test
  public void getPurchasesByEmailReturnsEmptyWith200() throws Exception {
    mockMvc.perform(get(PURCHASES_PATH + "/email/anyuser@email.com")).andExpect(status().isOk());
  }

  /**
   * Get purchases by email returns the right purchases
   *
   * @throws Exception
   */
  @Test
  public void getPurchasesByEmailReturnsRightPurchases() throws Exception {
    MvcResult PurchasesResult = mockMvc.perform(get(PURCHASES_PATH + "/email/bob@ross.com"))
        .andExpect(status().isOk()).andReturn();

    ObjectMapper mapper = new ObjectMapper();
    List<Purchase> purchases = mapper.readValue(PurchasesResult.getResponse().getContentAsString(),
        new TypeReference<List<Purchase>>() {
        });
    Assert.assertEquals(
        purchases.stream().filter(p -> p.getBillingAddress().getEmail().contains("bob@ross.com"))
            .count(), purchases.size());
  }
}
