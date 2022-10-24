package io.catalyte.training.superhealthapi.domains.patient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import io.catalyte.training.superhealthapi.domains.Patient.Patient;
import io.catalyte.training.superhealthapi.domains.Patient.PatientValidation;
import org.junit.Before;
import org.junit.Test;

public class PatientValidationTest {

  PatientValidation patientValidation;

  @Before
  public void setUp() {
    patientValidation = new PatientValidation();
  }

  /**
   * Will return true if the patient is valid
   */
  @Test
  public void patientIsValid() {
    Patient patient = new Patient("Johny", "Tucker", "654-90-3345", "JohnyFootball@gmail.com", "3456 W Seneca", "Cleveland", "OH", "56405",
        29F, 71F, 178F, "State Farm", "Male");
    boolean expected = true;
    boolean result = patientValidation.isValidPatient(patient);
    assertEquals(expected, result);
  }

}
