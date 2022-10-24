package io.catalyte.training.superhealthapi.domains.encounter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.catalyte.training.superhealthapi.domains.Encounter.Encounter;
import io.catalyte.training.superhealthapi.domains.Encounter.EncounterRepository;

import static io.catalyte.training.superhealthapi.constants.Paths.PATIENTS_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Testing the EncounterController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EncounterApiTest {

  @Mock
  EncounterRepository encounterRepository;

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
   * Path for getting all encounters belonging to certain patient returns a 200 OK status
   * @throws Exception
   */
  @Test
  public void getEncountersByPatientIdReturns200() throws Exception {
    mockMvc.perform(get(PATIENTS_PATH + "/2/encounters")).andExpect(status().isOk());
  }

  /**
   * Path for getting a single encounter based on patient id and encounter id returns a 200 OK status
   * @throws Exception
   */
  @Test
  public void getEncounterByEncounterIdReturns200() throws Exception {
    mockMvc.perform(get(PATIENTS_PATH + "/2/encounters/1")).andExpect(status().isOk());
  }

  /**
   * Post method for an encounter returns a 204 CREATED status
   * @throws Exception
   */
  @Test
  public void postEncounterReturns204() throws Exception {
    Encounter encounter = new Encounter(2L, "Gavem them cough syrup", "H8Y 8M3", "Aetna", "129.458.780-19", "B23",
        BigDecimal.valueOf(100), BigDecimal.valueOf(40), "Had a a bad cough",
        28F, 90F, 75F, "2022-10-09");

    mockMvc.perform(post(PATIENTS_PATH + "/2/encounters").contentType(MediaType.APPLICATION_JSON).content(
        asJsonString(encounter))).andExpect(status().isCreated());
  }

  /**
   * Put method for a encounter returns a 200 OK status
   * @throws Exception
   */
  @Test
  public void putEncounterReturns200() throws Exception {
    Encounter encounter = new Encounter(2L, "Gavem them cough syrup", "H8Y 8M3", "Aetna", "129.458.780-19", "B23",
        BigDecimal.valueOf(100), BigDecimal.valueOf(40), "Had a a bad cough",
        28F, 90F, 75F, "2022-10-09");
    encounter.setId(1L);

    MockHttpServletRequestBuilder builder = put(PATIENTS_PATH + "/2/encounters/1").contentType(
            MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .content(asJsonString(encounter));

    mockMvc.perform(builder).andExpect(status().isOk());
  }


}
