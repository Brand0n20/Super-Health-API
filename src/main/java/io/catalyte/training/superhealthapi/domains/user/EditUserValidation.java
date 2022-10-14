package io.catalyte.training.superhealthapi.domains.user;

/**
 * validates the user information before it gets updated
 */
public class EditUserValidation {

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

  /**
   * Checks if the user information is valid
   *
   * @param user - User information entered in RenderShippingAddress form in front-end
   * @return true if all fields validate, throws an exception if any field fails validation
   */
  boolean isValidUser(User user) {

    StringBuilder errorList = new StringBuilder();
    String firstName = user.getFirstName();
    String lastName = user.getLastName();
    String street = user.getStreet();
    String city = user.getCity();
    String state = user.getState();
    String zip = user.getZip();

    if (isBlankField(firstName)) {
      errorList.append("First Name must not be blank or null. ");
    } else if (!firstName.matches("^[a-zA-Z](?:[ '.\\-a-zA-Z]*[a-zA-Z])?$")) {
      errorList.append("First Name must only use letters,dashes, or apostrophes. ");
    }

    if (isBlankField(lastName)) {
      errorList.append("Last Name must not be blank or null. ");
    } else if (!lastName.matches("^[a-zA-Z](?:[ '.\\-a-zA-Z]*[a-zA-Z])?$")) {
      errorList.append("Last Name must only use letters,dashes, or apostrophes. ");
    }

    if (isBlankField(street)) {
      errorList.append("Street must not be blank or null. ");
    } else if (!street.matches("^[a-zA-Z0-9 ]*$")) {
      errorList.append("Street must contain only numbers, letters, and spaces. ");
    }

    if (isBlankField(city)) {
      errorList.append("City must not be blank or null. ");
    } else if (!city.matches("^([a-zA-Z ']*)$")) {
      errorList.append("City Name must only use letters,dashes, spaces, or apostrophes. ");
    }

    if (isBlankField(state)) {
      errorList.append("State must not be blank or null. ");
    } else if (!state.matches("^([a-zA-Z ']*)$")) {
      if (isBlankField(zip)) {
        errorList.append("Zip must not be blank or null. ");
      } else if (!zip.matches("^\\d{5}$") || !zip.matches("^\\d{5}-\\d{4}$")) {
        errorList.append("Zip must be format 12345 or 12345-6789. ");
      }
    }

    return true;
  }
}
