package io.catalyte.training.superhealthapi.domains.Encounter;

import io.catalyte.training.superhealthapi.domains.Patient.PatientRepository;
import io.catalyte.training.superhealthapi.exceptions.ResourceNotFound;
import io.catalyte.training.superhealthapi.exceptions.ServerError;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * This class provides the implementation of the EncounterService interface
 */
@Service
public class EncounterServiceImpl implements EncounterService {

  private final Logger logger = LogManager.getLogger(EncounterServiceImpl.class);

  private final EncounterRepository encounterRepository;

  private final PatientRepository patientRepository;

  public EncounterValidation encounterValidation = new EncounterValidation();


  @Autowired
  public EncounterServiceImpl(EncounterRepository encounterRepository,
      PatientRepository patientRepository) {
    this.encounterRepository = encounterRepository;
    this.patientRepository = patientRepository;
  }

  /**
   * Retrieves all the encounters belonging to a certain patient
   * @param id - patient id to find encounters by
   * @return - a list of encounters
   */
  @Override
  public List<Encounter> getAllEncountersByPatientId(long id) {
    try {
      return encounterRepository.findEncountersByPatientId(id);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  /**
   * Retrieves a single patient encounter based on the patient id and encounter id
   * @param patientId - way to identify encounters
   * @param encounterId - identifier for single encounter
   * @return - a single encounter
   */
  @Override
  public Encounter getSingleEncounterByPatientId(long patientId, long encounterId) {
    Encounter encounter = null;
    List<Long> encounterListIds = encounterRepository.findEncountersByPatientId(patientId).stream()
        .map(Encounter::getId).collect(
            Collectors.toList());
    if (encounterListIds.contains(encounterId)) {
      try {
        encounter = encounterRepository.findById(encounterId).orElse(null);
      } catch (DataAccessException e) {
        logger.error(e.getMessage());
        throw new ServerError(e.getMessage());
      }
    }
    return encounter;
  }

  /**
   * Will save an encounter object to a specific patient if that patient exists and the encounter is valid
   * Will also make sure the path patient id and the body patient id match
   * @param patientId - identifier for which patient the encounter will be posted under
   * @param encounter - the encounter to be saved
   * @return - a saved encounter
   */
  @Override
  public Encounter saveEncounter(long patientId, Encounter encounter) {
    if (patientRepository.existsById(patientId)) {
      if (patientId == encounter.getPatientId()) {
        encounterValidation.isValidEncounter(encounter);
        try {
          encounterRepository.save(encounter);
        } catch (DataAccessException e) {
          logger.error(e.getMessage());
          throw new ServerError(e.getMessage());
        }
      } else if (patientId != encounter.getPatientId()) {
        logger.error("Body patient id must match path variable id");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Body patient id must match path variable id");
      }
    } else {
      logger.error("Patient with id of " + patientId + " does not exist in the database");
      throw new ResourceNotFound(
          "Patient with id of " + patientId + " does not exist in the database. ");
    }
    return encounter;
  }

  /**
   *  Will update an encounter object to a specific patient if that patient exists,
   *  the encounter exists and the encounter is valid
   *  Makes sure the body id's match the path ids and then validates the encounter
   * @param patientId - identifier for which patient the encounter will be updated under
   * @param encounterId - identifier for single encounter
   * @param encounter - the newer encounter information to be saved
   * @return - the updated encounter
   */
  @Override
  public Encounter updateEncounter(long patientId, long encounterId, Encounter encounter) {
    Encounter updatedEncounter = null;
    if (patientRepository.existsById(patientId) && encounterRepository.existsById(encounterId)) {
      if (patientId == encounter.getPatientId() && encounterId == encounter.getId()) {
        encounterValidation.isValidEncounter(encounter);
        try {
          updatedEncounter = encounterRepository.save(encounter);
        } catch (DataAccessException e) {
          logger.error(e.getMessage());
          throw new ServerError(e.getMessage());
        }
      } else {
        logger.error("Both patient and encounter id need to match in the body and in the path. ");
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Both patient and encounter id need to match in the body and in the path. ");
      }
    } else if (!encounterRepository.existsById(encounterId)) {
      logger.error("Encounter with id of " + encounterId + " does not exist in the database");
      throw new ResourceNotFound(
          "Encounter with id of " + encounterId + " does not exist in the database");
    } else {
      logger.error("Patient with id of" + patientId + " does not exist in the database");
      throw new ResourceNotFound(
          "Patient with id of" + patientId + " does not exist in the database");
    }
    return updatedEncounter;
  }

}