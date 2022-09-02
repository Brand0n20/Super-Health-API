package io.catalyte.training.sportsproducts.domains.purchase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

public class CreditCardValidationTest {

  CreditCardValidation creditCardValidation;

  @Before
  public void setUp() {
    creditCardValidation = new CreditCardValidation();
  }

  /**
   * Validation for blank fields returns false if information fields are filled
   */
  @Test
  public void informationIsNotBlank() {

    Purchase input = new Purchase();
    input.setCreditCard(new CreditCard("1234567890123456", "333", "06/22", "Devon Castango"));

    boolean expected = false;

    boolean result = creditCardValidation.isBlankField(input.getCreditCard().getCardNumber());

    assertEquals(expected, result, "Field is empty, Expected: " + expected + ". Got: " + result);
  }

  /**
   * Validation for blank fields returns true if information fields are partially filled
   */
  @Test
  public void informationIsNull() {

    Purchase input = new Purchase();
    input.setCreditCard(new CreditCard("1234567890123456", null, "06/22", "Devon Castango"));

    boolean expected = true;

    boolean result = creditCardValidation.isBlankField(input.getCreditCard().getCvv());

    assertEquals(expected, result, "Field is not null, Expected: " + expected + ". Got" + result);
  }

  /**
   * Validation for expiration returns true if credit card is expired
   */
  @Test
  public void creditCardExpired() {

    Purchase input = new Purchase();
    input.setCreditCard(new CreditCard("1234567890123456", "333", "06/22", "Devon Castango"));

    boolean expected = true;

    boolean result = creditCardValidation.creditCardExpiration(input);

    assertEquals(expected, result,
        "Date is not expired, Expected: " + expected + ". Got: " + result);
  }

  /**
   * Validation for credit cards returns true if credit card is valid and all fields are filled
   * properly
   */
  @Test
  public void isValidCard() {

    Purchase input = new Purchase();
    input.setCreditCard(new CreditCard("1234567890123456", "333", "09/24", "Devon Castango"));

    boolean expected = true;

    boolean result = creditCardValidation.isValidCreditCard(input);

    assertEquals(expected, result,
        "Is not a valid Credit Card, Expected: " + expected + ". Got: " + result);
  }

  /**
   * Error message is thrown and correct when card fields are partially filled
   */
  @Test
  public void cardFieldsAreBlank() {
    Purchase input = new Purchase();
    input.setCreditCard(new CreditCard(null, null, null, ""));

    Exception thrown = assertThrows(ResponseStatusException.class,
        () -> creditCardValidation.isValidCreditCard(input));

    assertEquals(
        "400 BAD_REQUEST \"Card Number must not be blank or null. CVV must not be blank or null. Expiration must not be blank or null. Card Holder must not be blank or null. \"",
        thrown.getMessage());
  }

  /**
   * Error message is thrown and correct when card fields content doesn't pass validation
   */
  @Test
  public void cardFieldsDoNotFollowRegex() {
    Purchase input = new Purchase();
    input.setCreditCard(new CreditCard("aaaaaaaaaaaaaaaa", "aaa", "12/1", "Bob marely"));

    Exception thrown = assertThrows(ResponseStatusException.class,
        () -> creditCardValidation.isValidCreditCard(input));

    assertEquals(
        "400 BAD_REQUEST \"Card Number must only contain numbers, Entered: [aaaaaaaaaaaaaaaa]. CVV must only contain numbers, Entered: [aaa]. Expiration date must follow MM/yy format, Entered: [12/1]. Card holder name must contain first and last name, and can only contain alphabetic characters, hyphens, and apostrophes, Entered: [Bob marely]. \"",
        thrown.getMessage());
  }

  /**
   * Error message is thrown and correct when card fields length doesn't pass validation
   */
  @Test
  public void cardFieldsDoNotFollowLengthRestrictions() {
    Purchase input = new Purchase();
    input.setCreditCard(new CreditCard("12345678901234", "33", "12/24", "Bob Marely"));

    Exception thrown = assertThrows(ResponseStatusException.class,
        () -> creditCardValidation.isValidCreditCard(input));

    assertEquals(
        "400 BAD_REQUEST \"Card Number must be between 15 and 16 numbers long, Current Length: [14]. CVV must be 3 numbers long, Current Length: [2]. \"",
        thrown.getMessage());
  }

  /**
   * Error message is thrown and correct when credit card is expired
   */
  @Test
  public void cardExpired() {
    Purchase input = new Purchase();
    input.setCreditCard(new CreditCard("1234567890123456", "333", "12/21", "Bob Marely"));

    Exception thrown = assertThrows(ResponseStatusException.class,
        () -> creditCardValidation.isValidCreditCard(input));

    assertEquals("400 BAD_REQUEST \"Credit Card is expired, Entered: [12/21]. \"",
        thrown.getMessage());
  }
}