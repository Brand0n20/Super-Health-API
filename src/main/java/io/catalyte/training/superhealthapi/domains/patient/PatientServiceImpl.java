package io.catalyte.training.superhealthapi.domains.patient;

import io.catalyte.training.superhealthapi.exceptions.ResourceNotFound;
import io.catalyte.training.superhealthapi.exceptions.ServerError;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;



@Service
public class PatientServiceImpl implements PatientService{

  private final Logger logger = LogManager.getLogger(PatientServiceImpl.class);

  private final PatientRepository patientRepository;

  @Autowired
  public PatientServiceImpl(PatientRepository patientRepository) {
    this.patientRepository = patientRepository;
  }

  @Override
  public List<Patient> getAllPatients(Patient patient) {
    try {
      return patientRepository.findAll();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  @Override
  public Patient getPatientById(long patientId) {
    Patient patient;
    try {
      patient = patientRepository.findById(patientId).orElse(null);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    if (patient != null) {
      return patient;
    } else {
      logger.info("Patient with id " + patientId + " does not exist in the database");
      throw new ResourceNotFound("Patient with id " + patientId + " does not exist in the database");
    }
  }

  @Override
  public Patient savePatient(Patient patient) {
    return null;
  }

  @Override
  public Patient updatePatient(Patient patient, long patientId) {
    return null;
  }

  @Override
  public void deletePatient(long patientId) {

  }
}
