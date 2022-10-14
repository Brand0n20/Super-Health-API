package io.catalyte.training.superhealthapi.data;

import io.catalyte.training.superhealthapi.domains.patient.Patient;
import io.catalyte.training.superhealthapi.domains.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Because this class implements CommandLineRunner, the run method is executed as soon as the server
 * successfully starts and before it begins accepting requests from the outside. Here, we use this
 * as a place to run some code that generates and saves a list of random products into the
 * database.
 */
@Component
public class DemoData implements CommandLineRunner {
  @Autowired
  private PatientRepository patientRepository;

  @Override
  public void run(String... args) throws Exception {
    loadPatients();
  }

  private void loadPatients() {
    patientRepository.save(new Patient("Vivian", "Westwood", "452-98-9033", "vivian@gmail.com",
        "3423 W Chelsea", "Manhattan", "New York", "56705", 26, 66, 110, "Blue Cross", "Female"));

    patientRepository.save(new Patient("Alexander", "McQueen", "546-77-9987", "Dior@gmail.com", "6500 N Park Avenue", "Manhattan", "New York", "56717", 31, 71, 150, "Aetna", "Male"));
  }
}