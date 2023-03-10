package io.catalyte.training.superhealthapi.data;

import io.catalyte.training.superhealthapi.domains.Encounter.Encounter;
import io.catalyte.training.superhealthapi.domains.Encounter.EncounterRepository;
import io.catalyte.training.superhealthapi.domains.Patient.Patient;
import io.catalyte.training.superhealthapi.domains.Patient.PatientRepository;
import java.math.BigDecimal;
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

  @Autowired
  private EncounterRepository encounterRepository;

  @Override
  public void run(String... args) throws Exception {
    loadPatients();
    loadEncounters();
  }

  private void loadPatients() {
    patientRepository.save(new Patient("Vivian", "Westwood", "452-98-9033", "vivian@gmail.com",
        "3423 W Chelsea", "Manhattan", "NY", "56705", 26F, 66F, 110F, "Blue Cross", "Female"));

    patientRepository.save(new Patient("Alexander", "McQueen", "546-77-9987", "McQueen@gmail.com", "6500 N Park Avenue", "Manhattan", "NY", "56717", 31F, 71F, 150F, "Aetna", "Male"));
  }

  private void loadEncounters() {
    encounterRepository.save(new Encounter(2L, "Provided iboprofen for 3 days", "H7J 8W2", "Dr. Cole", "123.456.789-12", "A22",
        BigDecimal.valueOf(55.50), BigDecimal.valueOf(20), "Had a flue",
        23F, 100F, 80F, "2022-10-17"));

    encounterRepository.save(new Encounter(2L, "Gavem them cough syrup", "H8Y 8M3", "Dr. Umar", "129.458.780-19", "B23",
        BigDecimal.valueOf(100), BigDecimal.valueOf(40), "Had a a bad cough",
        28F, 90F, 75F, "2022-10-09"));
  }
}