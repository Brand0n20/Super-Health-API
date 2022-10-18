package io.catalyte.training.superhealthapi.domains.Encounter;

import io.catalyte.training.superhealthapi.domains.Patient.PatientRepository;
import io.catalyte.training.superhealthapi.exceptions.ResourceNotFound;
import io.catalyte.training.superhealthapi.exceptions.ServerError;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EncounterServiceImpl implements EncounterService{

  private final Logger logger = LogManager.getLogger(EncounterServiceImpl.class);

  private final EncounterRepository encounterRepository;

  private final PatientRepository patientRepository;


  @Autowired
  public EncounterServiceImpl(EncounterRepository encounterRepository, PatientRepository patientRepository) {
    this.encounterRepository = encounterRepository;
    this.patientRepository = patientRepository;
  }

  @Override
  public List<Encounter> getAllEncountersByPatientId(long id) {
    try {
      return encounterRepository.findEncountersByPatientId(id);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  @Override
  public Encounter getSingleEncounterByPatientId(long patientId, long encounterId) {
    Encounter encounter = null;
    List<Long> encounterListIds = encounterRepository.findEncountersByPatientId(patientId).stream().map(Encounter::getId).collect(
        Collectors.toList());
    if (encounterListIds.contains(encounterId)) {
      try {
        encounter =  encounterRepository.findById(encounterId).orElse(null);
      } catch (DataAccessException e) {
        logger.error(e.getMessage());
        throw new ServerError(e.getMessage());
      }
    }
    return encounter;
  }

  @Override
  public Encounter saveEncounter(long patientId, Encounter encounter) {
    Encounter savedEncounter = null;
    if (patientRepository.existsById(patientId)) {
      if (patientId == encounter.getPatientId()) {
        try {
          savedEncounter = encounterRepository.save(encounter);
        } catch (DataAccessException e) {
          logger.error(e.getMessage());
          throw new ServerError(e.getMessage());
        }
      } else if (patientId != encounter.getPatientId()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Body patient id must match path variable id");
      }
    } else {
      throw new ResourceNotFound("Patient with id of " + patientId + " does not exist in the database");
    }
    return savedEncounter;
  }

}
