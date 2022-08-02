package PromoCode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@WebMvcTest(PromoCodeController.class)
public class PromoCodeControllerTest {

  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;

  public PromoCodeControllerTest(PromoCode newPromoCode) {
    this.newPromoCode = newPromoCode;
  }

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }
  private final PromoCode newPromoCode;

  @Test
  public void createPromoCodeAPI() throws Exception
  {

  }



}

