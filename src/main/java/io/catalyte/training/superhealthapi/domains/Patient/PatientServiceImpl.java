package io.catalyte.training.superhealthapi.domains.Patient;

import io.catalyte.training.superhealthapi.domains.Encounter.Encounter;
import io.catalyte.training.superhealthapi.domains.Encounter.EncounterRepository;
import io.catalyte.training.superhealthapi.exceptions.ResourceNotFound;
import io.catalyte.training.superhealthapi.exceptions.ServerError;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class PatientServiceImpl implements PatientService{

  private final Logger logger = LogManager.getLogger(PatientServiceImpl.class);

  private final PatientRepository patientRepository;

  private final EncounterRepository encounterRepository;

  PatientValidation patientValidation = new PatientValidation();

  @Autowired
  public PatientServiceImpl(PatientRepository patientRepository,
      EncounterRepository encounterRepository) {
    this.patientRepository = patientRepository;
    this.encounterRepository = encounterRepository;
  }

  /**
   * Will get all the patients in the database
   * @param patient - the patient that will get added to the movie list
   * @return a list of all the patients in the database
   */
  @Override
  public List<Patient> getAllPatients(Patient patient) {
    try {
      return patientRepository.findAll();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  /**
   * Will get the patient by its id
   * @param patientId - id that will identify the movie
   * @return the patient by the id given
   */
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

  /**
   * Will post a patient to the database
   * @param patient - patient entity to be posted
   * @return the persisted movie
   */
  @Override
  public Patient savePatient(Patient patient) {
    patientValidation.isValidPatient(patient);
    Patient existingPatient = patientRepository.findByEmail(patient.getEmail());
    Patient savedPatient = null;
    if (existingPatient == null) {
      try {
        savedPatient =  patientRepository.save(patient);
      } catch (DataAccessException e) {
        logger.error(e.getMessage());
        throw new ServerError(e.getMessage());
      }
    } else {
      logger.error("That email is already in use");
      throw new ResponseStatusException(HttpStatus.CONFLICT, "That email is already in use");
    }
  return savedPatient;
  }

  /**
   * Will update existing patient information
   * @param patient - payload coming in
   * @param patientId - identifier for patient
   * @return - a newly updated movie
   */
  @Override
  public Patient updatePatient(Patient patient, long patientId) {
    Patient existingPatient = patientRepository.findById(patientId).orElse(null);
    Patient anotherPatient = patientRepository.findByEmail(patient.getEmail());

    if (existingPatient == null) {
      throw new ResourceNotFound("Patient with id: " + patientId + " does not exist");
    } else {
      if (Objects.equals(existingPatient.getEmail(), patient.getEmail())
          && Objects.equals(anotherPatient.getId(), existingPatient.getId()) || anotherPatient == null) {
        if (patientId != patient.getId()) {
          throw  new ResponseStatusException(HttpStatus.BAD_REQUEST,
              "Body patient id must match path variable id");
        }
        patientValidation.isValidPatient(patient);
        try {
          patient = patientRepository.save(patient);
        } catch (DataAccessException e) {
          logger.error(e.getMessage());
          throw new ServerError(e.getMessage());
        }
      } else {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "That email is already in use by somebody else");
      }
    }
    return patient;
  }

  /**
   * Will delete a patient if its id provided exists in the database
   * @param patientId
   */
  @Override
  public void deletePatient(long patientId) {

    List<Long> patientIdsWithEncounters = encounterRepository.findAll().stream().map(Encounter :: getPatientId).collect(
        Collectors.toList());
    if (patientRepository.existsById(patientId)) {
      if (!patientIdsWithEncounters.contains(patientId)) {
        try {
          patientRepository.deleteById(patientId);
        } catch (DataAccessException e) {
          logger.error(e.getMessage());
          throw new ServerError(e.getMessage());
        }
      } else {
        throw new ResponseStatusException(HttpStatus.CONFLICT, "That patient has encounters and therefore cannot be deleted");
      }

    } else {
      throw new ResourceNotFound("Patient with id " + patientId + " does not exist in the database");
    }
  }

}
