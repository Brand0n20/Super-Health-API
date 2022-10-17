package io.catalyte.training.superhealthapi.domains.Patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PatientValidation {
  /**
   * Checks if an input is blank or null
   *
   * @param input String any field of any card
   * @return true if blank or null and false if populated
   */
  boolean isBlankField(String input) {
    if (input != null) {
      return input.trim().isEmpty();
    }
    return true;
  }

  public boolean isValidPatient(Patient patient) {

    StringBuilder errorList = new StringBuilder();
    String[] genders = new String[]{"Male", "Female", "Other", "male", "female", "other"};
    List<String> genderArray = new ArrayList<>(Arrays.asList(genders));
    String firstName = patient.getFirstName();
    String lastName = patient.getLastName();
    String ssn = patient.getSsn();
    String email = patient.getEmail();
    String street = patient.getStreet();
    String city = patient.getCity();
    String state = patient.getState();
    String postal = patient.getPostal();
    Integer age = patient.getAge();
    Integer height = patient.getHeight();
    Integer weight = patient.getWeight();
    String insurance = patient.getInsurance();
    String gender = patient.getGender();

    if (isBlankField(firstName)) {
      errorList.append("First name cannot be blank or null. ");
    } else if (!firstName.matches("^[a-zA-Z']*$")) {
      errorList.append("Last name cannot contain special characters or numbers.  ");
    }

    if (isBlankField(lastName)) {
      errorList.append("Last name cannot be blank or null. ");
    } else if (!lastName.matches("^[a-zA-Z']*$")) {
      errorList.append("Last name ccannot contain special characters or numbers. ");
    }

    if (isBlankField(ssn)) {
      errorList.append("Social security number cannot be blank or null. ");
    } else if (!ssn.matches("^(?!219-09-9999|078-05-1120)(?!666|000|9\\d{2})\\d{3}-(?!00)\\d{2}-(?!0{4})\\d{4}$")) {
      errorList.append("Social Security number must match the format DDD-DD-DDDD, where D is any number. ");
    }

    if (isBlankField(email)) {
      errorList.append("Email cannot be blank or null. ");
    } else if (!email.matches("\\S+@\\S+\\.\\S+")) {
      errorList.append("Email must be valid. ");
    }

    if (isBlankField(street)) {
      errorList.append("Street cannot be blank or null. ");
    }

    if (isBlankField(city)) {
      errorList.append("City cannot be blank or null. ");
    } else if (!city.matches("^[a-zA-Z' ]*$")) {
      errorList.append("City cannot contain special characters or numbers. ");
    }

    if (isBlankField(state)) {
      errorList.append("State cannot be blank or null. ");
    } else if (!state.matches("^[a-zA-Z' ]*$")) {
      errorList.append("State cannot contain special characters or numbers");
    }

    if (isBlankField(postal)) {
      errorList.append("Postal cannot be blank or null. ");
    } else if (!postal.matches("^\\d{5}$")) {
      if (!postal.matches("^\\d{5}-\\d{4}$")) {
        errorList.append("Postal must be of format DDDDD or DDDDD-DDDD where D is any number. ");
      }
    }

    if (age == null || age <= 0) {
      errorList.append("Age must be a positive number. ");
    }

    if (height == null || age <= 0) {
      errorList.append("Height must be a positive number. ");
    }
    if (weight == null || age <= 0) {
      errorList.append("Weight must be a positive number. ");
    }

    if (isBlankField(insurance)) {
      errorList.append("Insurance cannot be blank or null. ");
    } else if (!insurance.matches("^[a-zA-Z' ]*$")) {
      errorList.append("Insurance cannot contain special characters or numbers. ");
    }

    if (isBlankField(gender)) {
      errorList.append("Gender cannot be blank or null. ");
    } else if (!genderArray.contains(gender)){
      errorList.append("Gender must be either Male, Female, or Other ");
      }

    if (!(errorList.length() == 0)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorList.toString());
    }


    return true;
  }

}
