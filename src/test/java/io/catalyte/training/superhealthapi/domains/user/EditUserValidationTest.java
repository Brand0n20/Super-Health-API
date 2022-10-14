package io.catalyte.training.superhealthapi.domains.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EditUserValidationTest {

  EditUserValidation editUserValidation;

  @BeforeEach
  public void setUp() {
    editUserValidation = new EditUserValidation();
  }

  /**
   * Validation for blank fields returns false if information fields are filled
   */
  @Test
  public void informationIsNotBlank() {
    User user;
    user = new User(22L, "brandonalfaro20@gmail.com", "Customer", "Brandon", "Alfaro",
        "3245 N Shadow Lakes", "Wichita", "Kansas", "67205", "2022/09/10/60/10");
    boolean expected = false;

    boolean result = editUserValidation.isBlankField(user.getEmail());

    assertEquals(expected, result, "Field is empty, Expected: " + expected + ".Got " + result);
  }

  @Test
  void isBlankField() {
    User user;
    user = new User(null, null, null, "", "", null, null, "", null);
    boolean expected = true;
    boolean result = editUserValidation.isValidUser(user);

    assertEquals(expected, result,
        "Fields are empty/null. Expected: " + expected + ".Got: " + result);
  }

  /**
   * Validation for editing user returns true if credit card is valid and all fields are filled
   * properly
   */
  @Test
  public void isValidUser() {
    User user;
    user = new User(22L, "brandonalfaro20@gmail.com", "Customer", "Brandon", "Alfaro",
        "3245 N Shadow Lakes", "Wichita", "Kansas", "67205", "2022/09/10/60/10");

    boolean expected = true;

    boolean result = editUserValidation.isValidUser(user);
    assertEquals(expected, result,
        "It's not a valid user, Expected: " + expected + ". Got: " + result);

  }
}