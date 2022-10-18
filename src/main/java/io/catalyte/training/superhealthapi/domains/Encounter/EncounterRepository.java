package io.catalyte.training.superhealthapi.domains.Encounter;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Encounter entity
 */
@Repository
public interface EncounterRepository extends JpaRepository<Encounter, Long> {

  List<Encounter> findEncountersByPatientId(long patientId);
}
