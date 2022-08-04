package io.catalyte.training.sportsproducts.domains.PromoCode;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.catalyte.training.sportsproducts.domains.promoCode.PromoCode;
import io.catalyte.training.sportsproducts.domains.promoCode.PromoCodeController;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static io.catalyte.training.sportsproducts.constants.Paths.PROMO_CODE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PromoCodeController.class)
public class PromoCodeApiTest {

  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;


  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  /**
   * Promo codes with valid fields can be added into database
   * @throws Exception
   */
  @Test
  public void savePromoCode() throws Exception {
    PromoCode example = new PromoCode("New Sale", "Percent", "15", "FALL2022");

    this.mockMvc.perform(
        post(PROMO_CODE).contentType(MediaType.APPLICATION_JSON).content(asJsonString(example))).andExpect(status().isCreated());

  }

  /**
   * Mapper to parse object into string for mockMvc
   *
   * @param obj - obj to parse
   * @return
   */
  private static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }





}

