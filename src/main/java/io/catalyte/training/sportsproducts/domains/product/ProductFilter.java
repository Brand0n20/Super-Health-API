package io.catalyte.training.sportsproducts.domains.product;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Product Filter to create a database query based on params passed into ProductController
 */
public class ProductFilter {

  private HashSet<String> filters = new HashSet<>(
      Arrays.asList("brand", "category", "demographic", "color",
          "material", "price", "min-price", "max-price"));

  private ArrayList<String> queryList = new ArrayList<>();

  private HashMap<String, Set<String>> uniqueParams = new HashMap<>();

  /**
   * Creates a query string based on the unique params that were passed into the Product Controller
   *
   * @return String that can query the database
   */
  public String createFilterQuery() {
    for (String key : this.uniqueParams.keySet()) {
      this.generateQuery(key);
    }
    return this.combineQueries();
  }

  /**
   * Creates unique parameters from the parameters that were passed from Product Controller
   *
   * @param params
   */
  public void createUniqueParams(Map<String, String> params) {
    for (Map.Entry<String, String> param : params.entrySet()) {
      String[] values = param.getValue().split(",");
      System.out.println(param.getKey());
      Set<String> valuesSet = new HashSet<>(Arrays.asList(this.removeWhiteSpaces(values)));
      this.uniqueParams.put(param.getKey().toLowerCase(), valuesSet);
    }
  }

  /**
   * Control function to generate a query based on the name of the key passed in
   *
   * @param key
   */
  private void generateQuery(String key) {
    switch (key) {
      case "price":
        generatePriceQuery(key);
        break;
      case "max-price":
      case "min-price":
        generateMinMaxPriceQuery(key);
        break;
      case "color":
        generateColorQuery(key);
        break;
      case "brand":
      case "demographic":
      case "category":
      case "material":
        generateDefaultQuery(key);
        break;
    }
  }

  /**
   * Generates default WHERE IN query for database
   *
   * @param key
   */
  private void generateDefaultQuery(String key) {
    ArrayList<String> formattedValues = this.formatParamValues(uniqueParams.get(key));
    String queryString = String.format("p.%s IN (%s)", key, String.join(", ", formattedValues));
    this.queryList.add(queryString);
  }

  /**
   * Generates a query to find products with a given color code
   *
   * @param key
   */
  private void generateColorQuery(String key) {
    ArrayList<String> formattedColorValues = this.formatColorParamValues(
        this.uniqueParams.get(key));
    String colorStringValue = String.join(", ", formattedColorValues);
    queryList.add(
        String.format("(p.primaryColorCode IN (%s) OR p.secondaryColorCode IN (%s))",
            colorStringValue, colorStringValue));
  }

  /**
   * Generates a query to find a product with a given price
   *
   * @param key
   */
  private void generatePriceQuery(String key) {
    ArrayList<String> formattedPriceValues = this.formatPriceValues(this.uniqueParams.get(key));
    String priceStringValue = String.join(", ", formattedPriceValues);
    queryList.add(
        String.format("p.price IN (%s)", priceStringValue)
    );
  }

  /**
   * Generates either a max or min price query based on the value of key
   *
   * @param key
   */
  private void generateMinMaxPriceQuery(String key) {
    ArrayList<String> formattedPriceValues = this.formatPriceValues(this.uniqueParams.get(key));
    if (key.equals("min-price")) {
      queryList.add(
          String.format("(p.price >= %s)", formattedPriceValues.get(0))
      );
    } else {
      queryList.add(
          String.format("(p.price <= %s)", formattedPriceValues.get(0))
      );
    }
  }

  /**
   * Formats value to be used to generate a valid query
   *
   * @param paramValues
   * @return - String formatted for IN queries
   */
  private ArrayList<String> formatParamValues(Set<String> paramValues) {
    ArrayList<String> formattedValues = new ArrayList<>();

    for (String value : paramValues) {
      if (value.contains("-")) {
        formattedValues.add(formatMultiWordValue(value));
      } else {
        formattedValues.add(formatSingleWordValue(value));
      }
    }
    return formattedValues;
  }

  /**
   * Formats a value that contains multiple words in String
   *
   * @param words
   * @return - String formatted for IN queries
   */
  private String formatMultiWordValue(String words) {
    String[] splitWordsArray = words.split("-");

    Arrays.stream(splitWordsArray)
        .map(word -> this.capitalizeWord(word))
        .collect(Collectors.toList()).toArray(splitWordsArray);

    return "'" + String.join(" ", splitWordsArray) + "'";
  }

  /**
   * Formats a value to be used for IN queries
   *
   * @param word
   * @return - Formatted string for IN queries
   */
  private String formatSingleWordValue(String word) {
    return "'" + this.capitalizeWord(word) + "'";
  }

  /**
   * Capitalizes any word that is passed in
   *
   * @param word
   * @return - Capitalized String
   */
  private String capitalizeWord(String word) {
    return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
  }

  /**
   * Removes whitespace in all words in Array
   *
   * @param words
   * @return - String Array of trimmed words
   */
  private String[] removeWhiteSpaces(String[] words) {
    String[] trimmedWordsArray = new String[words.length];

    return Arrays.stream(words).map(word -> word.trim()).collect(Collectors.toList())
        .toArray(trimmedWordsArray);
  }

  /**
   * Formats a color value for color queries
   *
   * @param colorValues
   * @return - Formatted color code String
   */
  private ArrayList<String> formatColorParamValues(Set<String> colorValues) {
    ArrayList<String> formattedColorValues = new ArrayList<>();

    formattedColorValues.addAll(
        colorValues.stream().map(value -> "'#" + value.toLowerCase() + "'").collect(
            Collectors.toList()));

    return formattedColorValues;
  }

  /**
   * Formats price value for product price queries
   *
   * @param priceValues
   * @return - Formatted Price String
   */
  private ArrayList<String> formatPriceValues(Set<String> priceValues) {
    ArrayList<String> formattedPriceValues = new ArrayList<>();

    formattedPriceValues.addAll(
        priceValues.stream().map(value -> value.replace("$", ""))
            .collect(Collectors.toList()));
    return formattedPriceValues;
  }

  /**
   * Combines all generated queries into one query for the database
   *
   * @return - Query String
   */
  private String combineQueries() {
    return String.format("SELECT p FROM Product p WHERE (%s)", String.join(" AND ", queryList));
  }

  /**
   * Validates all params based on whether it is a valid filter for products and valid values for
   * prices
   *
   * @return - Boolean for valid params
   */
  public Boolean validParams() {
    for (String key : this.uniqueParams.keySet()) {
      if (!filters.contains(key)) {
        return false;
      }

      if (key.equals("price") || key.equals("max-price") || key.equals("min-price")) {
        if (!this.validPriceValues(key)) {
          return false;
        }
      }

      if (key.equals("max-price") || key.equals("min-price")) {
        if (!this.validMaxMinValue(key)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Validates all price values with price regex
   *
   * @param key
   * @return - Boolean for valid price
   */
  private Boolean validPriceValues(String key) {
    String regex = "^\\d{0,8}(\\.\\d{1,2})?$";
    for (String priceValue : this.uniqueParams.get(key)) {
      String formattedValue = priceValue.replace("$", "");
      if (!Pattern.matches(regex, formattedValue)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Validates min and max key values; should only contain one value for each key
   *
   * @param key
   * @return Boolean for valid min or max value
   */
  private Boolean validMaxMinValue(String key) {
    if(this.uniqueParams.get(key).size() > 1) {
      return false;
    }
    return true;
  }
}
