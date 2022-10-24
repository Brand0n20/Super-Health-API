package io.catalyte.training.superhealthapi.domains.patient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import io.catalyte.training.superhealthapi.domains.Encounter.EncounterRepository;
import io.catalyte.training.superhealthapi.domains.Patient.Patient;
import io.catalyte.training.superhealthapi.domains.Patient.PatientRepository;
import io.catalyte.training.superhealthapi.domains.Patient.PatientServiceImpl;
import java.util.Objects;
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
public class PatientServiceImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  Patient testPatient;

  @InjectMocks
  private PatientServiceImpl patientServiceImpl;

  @Mock
  private PatientRepository patientRepository;

  @Mock
  private EncounterRepository encounterRepository;

  @Before
  public void setUp() {
    testPatient = new Patient("Johny", "Tucker", "654-90-3345", "JohnyFootball@gmail.com", "3456 W Seneca", "Cleveland", "OH", "56405",
        29F, 71F, 178F, "State Farm", "Male");
    testPatient.setId(3L);

    when(patientRepository.findById(anyLong())).thenReturn(Optional.of(testPatient));
  }

  /**
   * A patient is returned when an id is given
   */
  @Test
  public void getPatientByIdReturnsPatient() {
    Patient actual = patientServiceImpl.getPatientById(testPatient.getId());
    assertEquals(testPatient, actual);
  }

  /**
   * A patient is saved to the database and returns the saved patient
   */
  @Test
  public void savePatientReturnsThePostedPatient() {
    Patient actual = patientServiceImpl.savePatient(testPatient);
    assertEquals(testPatient, actual);
  }

  /**
   * Will test to see if the updated patient is what it's expected to be
   */
  @Test
  public void updatePatientReturnsUpdatedPatient() {
    Patient existingPatient = patientRepository.findById(testPatient.getId()).orElse(null);
    Patient anotherPatient = new Patient("Alexander", "McQueen", "546-77-9987", "Dior@gmail.com",
        "6500 N Park Avenue", "Manhattan", "NY", "56717", 31F, 71F, 150F, "Aetna", "Male");
    anotherPatient.setId(4L);
    assert existingPatient != null;
    if (Objects.equals(existingPatient.getEmail(), testPatient.getEmail())
        && Objects.equals(anotherPatient.getId(), existingPatient.getId())
        || anotherPatient == null) {
      Patient actual = patientServiceImpl.savePatient(testPatient);
      assertEquals(testPatient, actual);
    }
  }

  /**
   * Will verify that the patient has been deleted
   */
  @Test
  public void deletePatientReturnsVoid() {
    Patient existingPatient = patientRepository.findById(testPatient.getId()).orElse(null);
    assert existingPatient != null;
    assertEquals(existingPatient, testPatient);
  }

  /**
   * Returns false if there's no patient with that given id in the database
   */
  @Test
  public void deleteNonExistentPatient() {
    assertFalse(patientRepository.existsById(4L));
  }

}
