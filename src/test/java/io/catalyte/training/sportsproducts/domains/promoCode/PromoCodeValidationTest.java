package io.catalyte.training.sportsproducts.domains.promoCode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.server.ResponseStatusException;

public class PromoCodeValidationTest {

  PromoCodeValidation promoCodeValidation;

  @Before
  public void setUp() {

    promoCodeValidation = new PromoCodeValidation();
  }

  @Test
  public void informationIsNotBlank() {

    String input = String.valueOf(new PromoCode("New Sale", "Percent", "Rate", "FALL2022"));

    boolean expected = false;

    boolean result = promoCodeValidation.isFieldBlank(input);

    assertEquals(expected, result);
  }

  @Test
  public void informationIsNull() {
    PromoCode promoCode = new PromoCode("New Sale", null, "Rate", "FALL2022");
    String input = promoCode.getType();

    boolean expected = true;

    boolean result = promoCodeValidation.isFieldBlank(input);

    assertEquals(expected, result);
  }

  @Test
  public void PromoCodeFieldsDoNotFollowRegex() {
    PromoCode promoCode = new PromoCode("New sale", "asdfdfa", "-15", "fall2022");
    Exception thrown = assertThrows(ResponseStatusException.class,
        () -> promoCodeValidation.codeValidation(promoCode));

    assertEquals(
        "400 BAD_REQUEST \"You must give a valid (flat/percent) type. Title of promo code should contain only capital letters and/or numbers. \"",
        thrown.getMessage());
  }

  @Test
  public void cardFieldsAreBlank() {
    PromoCode promoCode = new PromoCode(null, null, null, "");

    Exception thrown = assertThrows(ResponseStatusException.class,
        () -> promoCodeValidation.codeValidation(promoCode));

    assertEquals(
        "400 BAD_REQUEST \"Description field value cannot be blank or null. Type field cannot be blank or null. Rate field cannot be blank or null. Title field cannot be blank or null. \"",
        thrown.getMessage());
  }

}
