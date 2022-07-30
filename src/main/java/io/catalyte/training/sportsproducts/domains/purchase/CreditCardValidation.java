package io.catalyte.training.sportsproducts.domains.purchase;

import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Validates any information before it can be saved as a Credit card
 */
public class CreditCardValidation {

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
   * Checks date entered against current month to check if card is expired
   *
   * @param purchase Purchase information from transaction
   * @return true if card is expired, false if card is not expired
   */
  boolean creditCardExpiration(Purchase purchase) {
    DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM/yy");
    YearMonth lastValidMonth;
    lastValidMonth = YearMonth.parse(purchase.getCreditCard().getExpiration(), monthFormatter);
    return YearMonth.now(ZoneId.systemDefault()).isAfter(lastValidMonth);
  }

  /**
   * Validates Credit Card information entered.
   *
   * @param purchase Purchase information from transaction
   * @return true if all fields validate, throws an exception if any field fails validation
   */
  public boolean isValidCreditCard(Purchase purchase) {
    StringBuilder errorList = new StringBuilder();

    String cardNumber = purchase.getCreditCard().getCardNumber();

    String cvv = purchase.getCreditCard().getCvv();

    String expiration = purchase.getCreditCard().getExpiration();

    String cardHolder = purchase.getCreditCard().getCardholder();

    if (isBlankField(cardNumber)) {
      errorList.append("Card Number must not be blank or null. ");
    } else if (!cardNumber.matches("^\\d*$")) {
      errorList.append("Card Number must only contain numbers, Entered: [").append(cardNumber)
          .append("]. ");
    } else if (!(cardNumber.length() == 15 || cardNumber.length() == 16)) {
      errorList.append("Card Number must be between 15 and 16 numbers long, Current Length: [")
          .append(cardNumber.length()).append("]. ");
    }

    if (isBlankField(cvv)) {
      errorList.append("CVV must not be blank or null. ");
    } else if (!cvv.matches("^\\d*$")) {
      errorList.append("CVV must only contain numbers, Entered: [").append(cvv).append("]. ");
    } else if (!(cvv.length() == 3)) {
      errorList.append("CVV must be 3 numbers long, Current Length: [").append(cvv.length())
          .append("]. ");
    }

    if (isBlankField(expiration)) {
      errorList.append("Expiration must not be blank or null. ");
    } else if (!expiration.matches("^(0[1-9]|1[0-2])\\/?(\\d{2})$")) {
      errorList.append("Expiration date must follow MM/yy format, Entered: [").append(expiration)
          .append("]. ");
    } else {
      if (creditCardExpiration(purchase)) {
        errorList.append("Credit Card is expired, Entered: [")
            .append(expiration).append("]. ");
      }
    }

    if (isBlankField(cardHolder)) {
      errorList.append("Card Holder must not be blank or null. ");
    } else if (!cardHolder.matches("^[A-Z][a-z-']+\\s[A-Z][a-z-']+$")) {
      errorList.append(
              "Must be a valid name Format: firstName lastName (first letter must be capital and a alphabetic can contain hyphens and apostrophes), Entered: [")
          .append(cardHolder).append("]. ");
    }

    if (!(errorList.length() == 0)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorList.toString());
    }
    return true;
  }
}
