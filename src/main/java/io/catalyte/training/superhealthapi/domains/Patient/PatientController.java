package io.catalyte.training.superhealthapi.domains.Patient;

import static io.catalyte.training.superhealthapi.constants.Paths.PATIENTS_PATH;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for Patient Entity
 */
@RestController
@RequestMapping(PATIENTS_PATH)
public class PatientController {

  Logger logger = LogManager.getLogger(PatientController.class);

  private final PatientServiceImpl patientService;

  public PatientController(PatientServiceImpl patientService) {
    this.patientService = patientService;
  }

  /**
   * Retrieves all the patients in the database
   * @param patient - The type of class added to the list
   * @return - list of patients that are in the database with a 200 status
   */
  @GetMapping
  public ResponseEntity<List<Patient>> getPatients(Patient patient) {
    return new ResponseEntity<>(patientService.getAllPatients(patient), HttpStatus.OK);
  }

  /**
   * Retrieves a singular movie based on its id
   * @param id - Used to identify the movie being looked for
   * @return - Movie with the provided id and with a 200 status
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<Patient> getPatientById(@PathVariable long id) {
    return new ResponseEntity<>(patientService.getPatientById(id), HttpStatus.OK);
  }

  /**
   * Will save a valid patient to the database
   * @param patient - The patient to post
   * @return - The posted patient object with a 201 status
   */
  @PostMapping
  public ResponseEntity<Patient> savePatient(@RequestBody Patient patient) {
    return new ResponseEntity<>(patientService.savePatient(patient), HttpStatus.CREATED);
  }

  /**
   * Will update an existing patient
   * @param patientToUpdate = The new patient payload
   * @param id - path to identify the patient to be selected
   * @return - the updated patient
   */
  @PutMapping(value = "/{id}")
  public ResponseEntity<Patient> updatePatient(@RequestBody Patient patientToUpdate, @PathVariable long id) {
    return new ResponseEntity<>(patientService.updatePatient(patientToUpdate, id), HttpStatus.OK);
  }

  /**
   * Will delete a patient based on its given id
   * @param id - way to identify patient
   * @return - a NO_CONTENT status of 204
   */
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deletePatient (@PathVariable long id) {
    patientService.deletePatient(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
