package io.catalyte.training.superhealthapi.domains.Encounter;

import java.math.BigDecimal;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EncounterValidation {

  /**
   * Checks if an input is blank or null
   * @param input String any field of any card
   * @return true if blank or null and false if populated
   */
  boolean isBlankField(String input) {
    if (input != null) {
      return input.trim().isEmpty();
    }
    return true;
  }

  /**
   * Checks if the encounter object is valid based on if it passes the conditions tested
   * Will add error messages to a list for every condition failed, if the list contains error messages, a BAD_REQUEST status will be thrown
   * @param encounter - encounter to be validated
   * @return true saying the encounter is valid
   */
  public boolean isValidEncounter(Encounter encounter) {
    StringBuilder errorList = new StringBuilder();
    String visitCode = encounter.getVisitCode();
    String provider = encounter.getProvider();
    String billingCode = encounter.getBillingCode();
    String icd10 = encounter.getIcd10();
    BigDecimal totalCost = encounter.getTotalCost();
    BigDecimal copay = encounter.getCopay();
    String chiefComplaint = encounter.getChiefComplaint();
    Float pulse = encounter.getPulse();
    Float systolic = encounter.getSystolic();
    Float diastolic = encounter.getDiastolic();
    String date = encounter.getDate();

    if (isBlankField(visitCode)) {
      errorList.append("Visit code must not be blank or null. ");
    } else if (!visitCode.matches("^[A-Z]{1}\\d{1}[A-Z]{1} \\d{1}[A-Z]{1}\\d{1}$")) {
      errorList.append("Visit Code must be of format LDL DLD(ex. A1S 2D3). ");
    }

    if (isBlankField(provider)) {
      errorList.append("Provider must not be blank or null. ");
    }
    if (isBlankField(billingCode)) {
      errorList.append("Billing code must not be blank or null. ");
    } else if (!billingCode.matches("^\\d{3}.\\d{3}.\\d{3}-\\d{2}$")) {
      errorList.append("Billing code must be of format xxx.xxx.xxx-xx(ex. 123-456-789-12). ");
    }

    if (isBlankField(icd10)) {
      errorList.append("Icd10 must not be blank or null. ");
    } else if (!icd10.matches("^[A-Z]{1}\\d{2}$")) {
      errorList.append("Icd10 must be of format LDD(ex. A12). ");
    }

    if (totalCost == null) {
      errorList.append("Total cost must not be blank or null. ");
    } else if (totalCost.compareTo(BigDecimal.ZERO) < 0) {
      errorList.append("Total cost must be greater than zero. ");
    }

    if (copay == null) {
      errorList.append("Copay must not be blank or null. ");
    } else if (copay.compareTo(BigDecimal.ZERO) < 0) {
      errorList.append("Copay must be greater than zero. ");
    }

    if (isBlankField(chiefComplaint)) {
      errorList.append("Chief complaint must not be blank or null. ");
    }
    if (pulse != null && pulse <= 0) {
      errorList.append("Pulse must be greater than zero. ");
    } else if (pulse != null && (pulse) % 1 != 0) {
      errorList.append("Pulse must be a whole number or at least divisible by 1. ");
    }

    if (systolic != null && systolic <= 0) {
      errorList.append("Systolic pressure must be greater than zero. ");
    } else if (systolic != null && (systolic) % 1 != 0) {
      errorList.append("Systolic pressure must be a whole number or at least divisible by 1. ");
    }

    if (diastolic != null && diastolic <= 0) {
      errorList.append("Diastolic pressure must be greater than zero. ");
    } else if (diastolic != null && (diastolic) % 1 != 0) {
      errorList.append("Diastolic pressure must be a whole number or at least divisible by 1. ");
    }

    if (isBlankField(date)) {
      errorList.append("Visit code must not be blank or null. ");
    } else if (!date.matches("^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$")) {
      errorList.append("Date must be in the format YYYY-MM-DD(ex. 2020-10-01). ");
    }

    if (!(errorList.length() == 0)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorList.toString());
    }

    return true;
  }
}
