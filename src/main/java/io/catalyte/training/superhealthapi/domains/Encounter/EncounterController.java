package io.catalyte.training.superhealthapi.domains.Encounter;

import static io.catalyte.training.superhealthapi.constants.Paths.PATIENTS_PATH;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The EncounterController exposes endpoints for encounter related actions
 */
@RestController
@RequestMapping(value = PATIENTS_PATH)
public class EncounterController {

  private final EncounterServiceImpl encounterService;

  @Autowired
  public EncounterController(EncounterServiceImpl encounterService) {
    this.encounterService = encounterService;
  }

  /**
   * Retrieves all the encounters belonging to a certain patient
   * @param id - patient id to find the encounters
   * @return - a list of encounters
   */
  @GetMapping(value = "/{id}/encounters")
  public ResponseEntity<List<Encounter>> getEncountersByPatientId(@PathVariable long id) {
    return new ResponseEntity<>(encounterService.getAllEncountersByPatientId(id), HttpStatus.OK);
  }

  /**
   * Retrieves a single patient encounter based on the patient id and encounter id
   * @param id - patient id to find the encounters
   * @param encounterId - id to find a specific encounter
   * @return - a single Encounter object
   */
  @GetMapping(value = "/{id}/encounters/{encounterId}")
  public ResponseEntity<Encounter> getEncountersById(@PathVariable long id, @PathVariable long encounterId) {
    return new ResponseEntity<>(encounterService.getSingleEncounterByPatientId(id, encounterId), HttpStatus.OK);
  }

  /**
   * Will save a valid encounter to the database and under a specific patient's id
   * @param id - the id of which patient will be linked to this encounter to be saved
   * @param encounter - the payload that will be coming in
   * @return - the posted encounter
   */
  @PostMapping(value = "/{id}/encounters")
  public ResponseEntity<Encounter> saveEncounter(@PathVariable long id, @RequestBody Encounter encounter) {
    return new ResponseEntity<>(encounterService.saveEncounter(id, encounter), HttpStatus.CREATED);
  }

  /**
   * Will update an existing encounter
   * @param id - the id of which patient will be linked to this encounter to be updated
   * @param encounterId - id to find the specific encounter to be updated
   * @param encounter - the new payload coming in
   * @return - the updated encounter
   */
  @PutMapping(value = "/{id}/encounters/{encounterId}")
  public ResponseEntity<Encounter> saveEncounter(@PathVariable long id, @PathVariable long encounterId, @RequestBody Encounter encounter) {
    return new ResponseEntity<>(encounterService.updateEncounter(id, encounterId, encounter), HttpStatus.OK);
  }
}
