package io.catalyte.training.superhealthapi.domains.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Patient repository
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
  Patient findByEmail(String email);
}
