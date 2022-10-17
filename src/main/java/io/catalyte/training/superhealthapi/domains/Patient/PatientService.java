package io.catalyte.training.superhealthapi.domains.Patient;

import java.util.List;

/**
 * This interface provides an abstraction layer for the Patient Service
 */
public interface PatientService {
  List<Patient> getAllPatients(Patient patient);

  Patient getPatientById(long patientId);

  Patient savePatient(Patient patient);

  Patient updatePatient(Patient patient, long patientId);

  void deletePatient(long patientId);
}
