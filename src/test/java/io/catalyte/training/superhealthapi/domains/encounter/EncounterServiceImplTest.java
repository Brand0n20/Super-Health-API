package io.catalyte.training.superhealthapi.domains.encounter;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import io.catalyte.training.superhealthapi.domains.Encounter.Encounter;
import io.catalyte.training.superhealthapi.domains.Encounter.EncounterRepository;
import io.catalyte.training.superhealthapi.domains.Encounter.EncounterServiceImpl;
import io.catalyte.training.superhealthapi.domains.Patient.Patient;
import io.catalyte.training.superhealthapi.domains.Patient.PatientRepository;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest(EncounterServiceImpl.class)
public class EncounterServiceImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  Encounter encounter;

  Patient patient;

  @Mock
  EncounterRepository encounterRepository;

  @Mock
  PatientRepository patientRepository;

  @InjectMocks
  EncounterServiceImpl encounterServiceImpl;

  @Before
  public void setUp() {
    encounter = new Encounter(1L, "Provided iboprofen for 3 days", "H7J 8W2", "Blue Cross", "123.456.789-12", "A22",
        BigDecimal.valueOf(55.50), BigDecimal.valueOf(20), "Had a flue",
        23F, 100F, 80F, "2022-10-17");
    encounter.setId(3L);
    patient = new Patient("Vivian", "Westwood", "452-98-9033", "vivian@gmail.com",
        "3423 W Chelsea", "Manhattan", "NY", "56705", 26F, 66F, 110F, "Blue Cross", "Female");
    patient.setId(1L);
  }

  /**
   * A null value is returned if the patient id is not recognized
   */
  @Test
  public void getEncounterByPatientIdReturnsEncounter() {
    Encounter actual = encounterServiceImpl.getSingleEncounterByPatientId(encounter.getPatientId(), encounter.getId());
    Encounter otherEncounter = encounterServiceImpl.getSingleEncounterByPatientId(encounter.getPatientId(), encounter.getId());
    System.out.println(actual);
    assertEquals(otherEncounter, actual);
  }


}
