package io.catalyte.training.sportsproducts.domains.promoCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Validates through title and rate inputs
 */
public class PromoCodeValidation {
  public void codeValidation(PromoCode code) {
    StringBuilder errorList = new StringBuilder();
    String title = code.getTitle();
    if (code != null) {
      String type = code.getType();
      if (type.equals("percent") || type.equals("Percent")) {
        Pattern p = Pattern.compile("^[1-9]\\d?$");
        Matcher m = p.matcher(code.getRate());
        if (!m.matches()) {
          errorList.append("Percent should just be whole number and should be between 1-99. ");
        }
      } else if (type.equals("flat") || type.equals("Flat")) {
        Pattern p = Pattern.compile("^\\d{1,5}\\.\\d{2}$");
        Matcher m = p.matcher(code.getRate());
        if (!m.find()) {
          errorList.append("Flat should always have two decimal precision, and should be positive non-zero number. ");
        }
      }
      else {
        errorList.append("You must give a valid(flat/percent) type");
      }
      Pattern p = Pattern.compile("^[A-Z\\d]+$");
      Matcher m = p.matcher(title);
      if (!m.matches()) {
        errorList.append("Title of promo code should contain only capital letters and/or numbers");
      }

    } else {
      errorList.append("Object must not be null");
    }
    if (errorList.length() != 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorList.toString());
    }
  }
}
