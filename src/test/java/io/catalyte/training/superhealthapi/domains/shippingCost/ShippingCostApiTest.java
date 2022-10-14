package io.catalyte.training.superhealthapi.domains.shippingCost;

import static io.catalyte.training.superhealthapi.constants.Paths.SHIPPINGCOST_Path;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class ShippingCostApiTest {

  @Autowired
  ShippingCostRepository shippingCostRepository;
  @Autowired
  private WebApplicationContext wac;
  private MockMvc mockMvc;

  @Before
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
  }

  @Test
  public void getShippingCostListReturns200() throws Exception {
    mockMvc.perform(get(SHIPPINGCOST_Path))
        .andExpect(status().isOk());
  }

  @Test
  public void getCostByStateReturns200() throws Exception {
    mockMvc.perform(get(SHIPPINGCOST_Path + "/Kansas"))
        .andExpect(status().isOk());
  }

}
