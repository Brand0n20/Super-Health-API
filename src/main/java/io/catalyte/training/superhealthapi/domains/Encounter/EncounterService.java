package io.catalyte.training.superhealthapi.domains.Encounter;

import java.util.List;

/**
 * This interface provides an abstraction layer for the Encounter Service
 */
public interface EncounterService {

  List<Encounter> getAllEncountersByPatientId(long patientId);

  Encounter getSingleEncounterByPatientId(long patientId, long encounterById);

  Encounter saveEncounter(long patientId, Encounter encounter);

  Encounter updateEncounter(long patientId, long encounterId, Encounter encounter);

}
