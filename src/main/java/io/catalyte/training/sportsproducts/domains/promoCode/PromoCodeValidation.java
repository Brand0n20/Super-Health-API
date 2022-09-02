package io.catalyte.training.sportsproducts.domains.promoCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Validates through title and rate inputs
 */
public class PromoCodeValidation {

  /**
   * Checks if an input is blank or null
   *
   * @param input String any field of any card
   * @return true if blank or null and false if populated
   */
  public Boolean isFieldBlank(String input) {
    if (input != null) {
      return input.trim().isEmpty();
    }
    return true;
  }

  /**
   * Checks over each field and sees if any is empty, also validates based on given values
   *
   * @param code - promo code entered by user
   * @return true
   */
  public boolean codeValidation(PromoCode code) {
    StringBuilder errorList = new StringBuilder();
    String title = code.getTitle();
    String type = code.getType();
    String rate = code.getRate();
    String description = code.getDescription();

    if (isFieldBlank(description)) {
      errorList.append("Description field value cannot be blank or null. ");
    }
    if (isFieldBlank(type)) {
      errorList.append("Type field cannot be blank or null. ");
    } else if (type.equals("percent") || type.equals("Percent") || type.equals("PERCENT")) {
      Pattern p = Pattern.compile("^[1-9]\\d?$");
      if (rate != null) {
        Matcher m = p.matcher(rate);
        if (!m.matches()) {
          errorList.append("Percent should just be whole number and should be between 1-99. ");
        }
      } else if (rate == null) {
        errorList.append("Rate field cannot be blank or null. ");
      }

    } else if (type.equals("flat") || type.equals("Flat") || type.equals("FLAT")) {
      Pattern p = Pattern.compile("^\\d{1,5}\\.\\d{2}$");
      if (rate != null) {
        Matcher m = p.matcher(rate);
        if (!m.find()) {
          errorList.append(
              "Flat should always have two decimal precision, and should be positive non-zero number. ");
        }
      } else if (rate == null) {
        errorList.append("Rate field cannot be blank or null. ");
      }
    } else {
      errorList.append("You must give a valid (flat/percent) type. ");
    }
    if (isFieldBlank(rate)) {
      errorList.append("Rate field cannot be blank or null. ");
    }

    if (isFieldBlank(title)) {
      errorList.append("Title field cannot be blank or null. ");
    } else {
      Pattern p = Pattern.compile("^[A-Z\\d]+$");
      Matcher m = p.matcher(title);
      if (!m.find()) {
        errorList.append(
            "Title of promo code should contain only capital letters and/or numbers. ");
      }
    }

    if (errorList.length() != 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorList.toString());
    }
    return true;
  }

}
