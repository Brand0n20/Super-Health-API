package io.catalyte.training.superhealthapi.domains.patient;

import static io.catalyte.training.superhealthapi.constants.Paths.PATIENTS_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.catalyte.training.superhealthapi.domains.Patient.Patient;
import io.catalyte.training.superhealthapi.domains.Patient.PatientRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientApiTest {

  @Mock
  PatientRepository patientRepository;

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
   * Will return all patients will a 200 OK status
   * @throws Exception
   */
  @Test
  public void getPatientsReturns200() throws Exception {
    mockMvc.perform(get(PATIENTS_PATH)).andExpect(status().isOk());
  }

  /**
   * Will return a single patient based on the given id with a 200 OK status
   * @throws Exception
   */
  @Test
  public void getPatientByIdReturns200() throws Exception {
    mockMvc.perform(get(PATIENTS_PATH + "/2")).andExpect(status().isOk());
  }

  /**
   * Will throw an exception since the patient id doesn't exist
   * @throws Exception
   */
  @Test
  public void getPatientByNonExistingIdReturns404() throws Exception {
    mockMvc.perform(get(PATIENTS_PATH + "/4")).andExpect(status().isNotFound());
  }

  /**
   * Will return a saved patient with a 204 CREATED status
   * @throws Exception
   */
  @Test
  public void savePatientReturns201() throws Exception {
    Patient patient = new Patient("Tory", "Williams", "456-78-2345", "TW@gmail.com", "2347 W Park", "Overland Park", "KS",
        "45609", 34F, 71F, 168F, "Aetna", "Male");
    mockMvc.perform(post(PATIENTS_PATH).contentType(MediaType.APPLICATION_JSON).content(
        asJsonString(patient))).andExpect(status().isCreated());
  }

  /**
   * Will throw an exception if the patient email
   * @throws Exception
   */
  @Test
  public void patientWithOtherPatientEmailReturns409() throws Exception {
    Patient oldPatient = new Patient("Tory", "Williams", "456-78-2345", "TW@gmail.com", "2347 W Park", "Overland Park", "KS",
        "45609", 34F, 71F, 168F, "Aetna", "Male");
    patientRepository.save(oldPatient);

    Patient patientToSave = new Patient("Ricky", "Turner", "236-14-9580", "TW@gmail.com", "2540 E Grove", "Overland Park", "KS",
        "45610", 28F, 71F, 175F, "Blue Cross", "Male");

    Patient existingPatient = patientRepository.findByEmail(patientToSave.getEmail());

    if (existingPatient != null) {
      mockMvc.perform(post(PATIENTS_PATH).contentType(MediaType.APPLICATION_JSON).content(
          asJsonString(patientToSave))).andExpect(status().isConflict());
      }

  }

  @Test
  public void updatePatientReturns200() throws Exception {
    Patient patient = new Patient("Tory", "Williams", "456-78-2345", "TW@gmail.com", "2347 W Park", "Overland Park", "KS",
        "45609", 34F, 71F, 168F, "Aetna", "Male");
    patient.setId(1L);
    MockHttpServletRequestBuilder builder = put(PATIENTS_PATH + "/1").contentType(
            MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        .content(asJsonString(patient));

    mockMvc.perform(builder).andExpect(status().isOk());
  }


  @Test
  public void deletePatientReturns204() throws Exception {
    MockHttpServletRequestBuilder builder = delete(PATIENTS_PATH + "/1");
    mockMvc.perform(builder).andExpect(status().isNoContent());
  }

}
