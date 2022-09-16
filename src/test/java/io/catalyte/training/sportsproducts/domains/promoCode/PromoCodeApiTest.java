package io.catalyte.training.sportsproducts.domains.promoCode;

import static io.catalyte.training.sportsproducts.constants.Paths.PROMO_CODE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.catalyte.training.sportsproducts.constants.promoCode.PromoCode;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PromoCodeApiTest {

  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;

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

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void savePromoCodeHappyPath() throws Exception {
    PromoCode example = new PromoCode("FALL2022", "Percent", "New Sale", "15");

    mockMvc.perform(
            post(PROMO_CODE).contentType(MediaType.APPLICATION_JSON).content(asJsonString(example)))
        .andExpect(status().isCreated());

  }
}

