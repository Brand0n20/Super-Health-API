package io.catalyte.training.superhealthapi.domains.Encounter;

import static io.catalyte.training.superhealthapi.constants.Paths.PATIENTS_PATH;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = PATIENTS_PATH)
public class EncounterController {

  private final EncounterServiceImpl encounterService;

  @Autowired
  public EncounterController(EncounterServiceImpl encounterService) {
    this.encounterService = encounterService;
  }

  @GetMapping(value = "/{id}/encounters")
  public ResponseEntity<List<Encounter>> getEncountersByPatientId(@PathVariable long id) {
    return new ResponseEntity<>(encounterService.getAllEncountersByPatientId(id), HttpStatus.OK);
  }

  @GetMapping(value = "/{id}/encounters/{encounterId}")
  public ResponseEntity<Encounter> getEncountersById(@PathVariable long id, @PathVariable long encounterId) {
    return new ResponseEntity<>(encounterService.getSingleEncounterByPatientId(id, encounterId), HttpStatus.OK);
  }

  @PostMapping(value = "/{id}/encounters")
  public ResponseEntity<Encounter> saveEncounter(@PathVariable long id, @RequestBody Encounter encounter) {
    return new ResponseEntity<>(encounterService.saveEncounter(id, encounter), HttpStatus.CREATED);
  }

  @PutMapping(value = "/{id}/encounters/{encounterId}")
  public ResponseEntity<Encounter> saveEncounter(@PathVariable long id, @PathVariable long encounterId, @RequestBody Encounter encounter) {
    return new ResponseEntity<>(encounterService.updateEncounter(id, encounterId, encounter), HttpStatus.OK);
  }
}
