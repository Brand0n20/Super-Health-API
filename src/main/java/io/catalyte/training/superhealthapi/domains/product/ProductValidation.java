package io.catalyte.training.superhealthapi.domains.product;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * Methods that ensure entered product information is not bad
 */
public class ProductValidation {

  StringBuilder errorList = new StringBuilder();

  /**
   * Checks if any string is blank or null
   *
   * @param input String that needs to be checked
   * @return true if null or blank
   */
  boolean isBlankField(String input) {
    if (input != null) {
      return input.trim().isEmpty();
    }
    return true;
  }

  /**
   * Checks if any integer is less than zero or null
   *
   * @param number
   * @return
   */
  boolean isNotEmptyInteger(Integer number) {
    if (number != null) {
      return number < 0;
    }
    return true;
  }

  /**
   * Checks that any product field does not accept bad information
   *
   * @param input String to be checked
   */
  void fieldIsNotBlank(String input, String message) {

    if (isBlankField(input)) {
      errorList.append(message);
    }
  }

  /**
   * Checks if a field is valid
   *
   * @param input              {String} the product field to check
   * @param message            {String} the null or blank error
   * @param restrictionMessage {String} the regex length error
   * @param regex              {String} the regular expression
   * @param lengthRestriction  {Integer} the length restriction
   */
  void notValidField(String input, String message, String restrictionMessage, String regex,
      Integer lengthRestriction) {
    if (isBlankField(input)) {
      errorList.append(message);
    } else if (!input.matches(regex) || input.length() != lengthRestriction) {
      errorList.append(restrictionMessage);
    }
  }

  /**
   * Checks if field is blank or null if that is its only restriction
   *
   * @param product product being passed in
   */
  void fieldsAreNotBlank(Product product) {
    fieldIsNotBlank(product.getName(), "Name must not be empty or null. ");
    fieldIsNotBlank(product.getBrand(), "Brand must not be empty or null. ");
    fieldIsNotBlank(product.getType(), "Type must not be empty or null. ");
    fieldIsNotBlank(product.getDemographic(), "Demographic must not be empty or null. ");
    fieldIsNotBlank(product.getMaterial(), "Material must not be empty or null. ");
    fieldIsNotBlank(product.getCategory(), "Category must not be empty or null. ");
    fieldIsNotBlank(product.getDescription(), "Description must not be blank or null. ");
  }

  /**
   * Adds error messages to error list if a field is not valid
   *
   * @param product Product to be verified
   * @return boolean if false or list of errors
   */
  boolean validProduct(Product product) {
    errorList.setLength(0);

    fieldsAreNotBlank(product);

    notValidField(product.getPrimaryColorCode(), "Primary color code must not be empty or null. ",
        "Primary color code must start with # and have exactly 6 characters e.g. #3120E0. ",
        "^#[a-fA-F0-9]+$", 7);

    notValidField(product.getSecondaryColorCode(),
        "Secondary color code must not be empty or null. ",
        "Secondary color code must start with # and have exactly 6 characters e.g. #3120E0. ",
        "^#[a-fA-F0-9]+$", 7);

    notValidField(product.getStyleNumber(), "Style Number must not be blank or null. ",
        "Style Number must only be sc followed by 5 numbers. ", "^sc[0-9]+[0-9]*$", 7);

    notValidField(product.getGlobalProductCode(), "Product code must not be blank or null. ",
        "Product code must be po- followed by 7 numbers ", "^po-[0-9]+[0-9]*$", 10);

    notValidField(product.getImageSrc(), "Image src must not be empty or null. ",
        "Image src must contain a valid URL e.g. (https://jiffyshirts.imgix.net/). ",
        "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._+~#=,]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_+.~#?&/=]*)$",
        product.getImageSrc().length());

    notValidField(product.getReleaseDate(), "Release date must not be empty or null. ",
        "Release date must be in MM/DD/YYYY format. ",
        "^(0[1-9]|1[012])[/](0[1-9]|[12][0-9]|3[01])[/](19|20)\\d\\d$", 10);

    if (isNotEmptyInteger(product.getQuantity())) {
      errorList.append("Quantity must not be empty or null. ");
    }

    if (!(errorList.length() == 0)) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorList.toString());
    }
    return false;
  }

}
