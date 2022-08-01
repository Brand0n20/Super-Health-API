package io.catalyte.training.sportsproducts.domains.product;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProductFilter {

  private HashSet<String> filters = new HashSet<>(
      Arrays.asList("brand", "category", "demographic", "color",
          "material", "price", "prices", "min", "max"));

  private ArrayList<String> queryList = new ArrayList<>();

  private HashMap<String, Set<String>> uniqueParams = new HashMap<>();

  public String createFilterQuery() {
    for (String key : this.uniqueParams.keySet()) {
      this.generateQuery(key);
    }
    return this.combineQueries();
  }

  public void createUniqueParams(Map<String, String> params) {
    for (Map.Entry<String, String> param : params.entrySet()) {
      String[] values = param.getValue().split(",");
      Set<String> valuesSet = new HashSet<>(Arrays.asList(values));
      this.uniqueParams.put(param.getKey().toLowerCase(), valuesSet);
    }
  }

  private void generateQuery(String key) {
    switch (key) {
      case "price":
      case "prices":
        generatePriceQuery(key);
        break;
      case "max":
      case "min":
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

  private void generateDefaultQuery(String key) {
    ArrayList<String> formattedValues = this.formatParamValues(uniqueParams.get(key));
    String queryString = String.format("p.%s IN (%s)", key, String.join(", ", formattedValues));
    this.queryList.add(queryString);
  }

  private void generateColorQuery(String key) {
    ArrayList<String> formattedColorValues = this.formatColorParamValues(
        this.uniqueParams.get(key));
    String colorStringValue = String.join(", ", formattedColorValues);
    queryList.add(
        String.format("(p.primaryColorCode IN (%s) OR p.secondaryColorCode IN (%s))",
            colorStringValue, colorStringValue));
  }

  private void generatePriceQuery(String key) {
    ArrayList<String> formattedPriceValues = this.formatPriceValues(this.uniqueParams.get(key));
    String priceStringValue = String.join(", ", formattedPriceValues);
    queryList.add(
        String.format("p.price IN (%s)", priceStringValue)
    );
  }

  private void generateMinMaxPriceQuery(String key) {
    ArrayList<String> formattedPriceValues = this.formatPriceValues(this.uniqueParams.get(key));
    if (key.equals("min")) {
      queryList.add(
          String.format("(p.price >= %s)", formattedPriceValues.get(0))
      );
    } else {
      queryList.add(
          String.format("(p.price <= %s)", formattedPriceValues.get(0))
      );
    }
  }

  private ArrayList<String> formatParamValues(Set<String> paramValues) {
    ArrayList<String> formattedValues = new ArrayList<>();

    for (String value : paramValues) {
      String trimmedValue = value.trim();
      if (trimmedValue.contains(" ")) {
        formattedValues.add(formatMultiWordValue(value));
      } else {
        formattedValues.add(formatSingleWordValue(value));
      }
    }
    return formattedValues;
  }

  private String formatMultiWordValue(String value) {
    String[] splitWordsArray = value.split(" ");

    Arrays.stream(splitWordsArray)
        .map(word -> this.capitalizeWord(word))
        .collect(Collectors.toList()).toArray(splitWordsArray);

    return "'" + String.join(" ", splitWordsArray) + "'";
  }

  private String formatSingleWordValue(String word) {
    return "'" + this.capitalizeWord(word) + "'";
  }

  private String capitalizeWord(String word) {
    return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
  }

  private ArrayList<String> formatColorParamValues(Set<String> colorValues) {
    ArrayList<String> formattedColorValues = new ArrayList<>();

    formattedColorValues.addAll(
        colorValues.stream().map(value -> "'#" + value.toLowerCase() + "'").collect(
            Collectors.toList()));

    return formattedColorValues;
  }

  private ArrayList<String> formatPriceValues(Set<String> priceValues) {

    ArrayList<String> formattedPriceValues = new ArrayList<>();

    formattedPriceValues.addAll(
        priceValues.stream().map(value -> value.replace("$", ""))
            .collect(Collectors.toList()));
    return formattedPriceValues;
  }

  private String combineQueries() {
    return String.format("SELECT p FROM Product p WHERE (%s)", String.join(" AND ", queryList));
  }

  public Boolean validParams() {
    for (String key : this.uniqueParams.keySet()) {
      if (!filters.contains(key)) {
        return false;
      }

      if (key.equals("price") || key.equals("prices") || key.equals("max") || key.equals("min")) {
        if (!this.validPriceValues(key)) {
          return false;
        }
      }

      if (key.equals("max") || key.equals("min")) {
        if (!this.validMaxMinValue(key)) {
          return false;
        }
      }
    }
    return true;
  }

  public Boolean validPriceValues(String key) {
    String regex = "^\\d{0,8}(\\.\\d{1,2})?$";
    for (String priceValue : this.uniqueParams.get(key)) {
      String formattedValue = priceValue.replace("$", "");
      if (!Pattern.matches(regex, formattedValue)) {
        return false;
      }
    }
    return true;
  }

  public Boolean validMaxMinValue(String key) {
    if (this.uniqueParams.get(key).size() > 1) {
      return false;
    }
    return true;
  }

}
