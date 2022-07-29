package io.catalyte.training.sportsproducts.domains.product;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductFilter {

  private String[] currentFilters = new String[]{"brand", "category", "demographic", "color",
      "material", "price", "prices", "min", "max"};

  private ArrayList<String> queryList = new ArrayList<>();

  private HashMap<String, Set<String>> uniqueParams;

  public String createFilterQuery(HashMap<String, Set<String>> params) {
    this.uniqueParams = params;
    for (String key : params.keySet()) {
      this.generateQuery(key);
    }
    return this.combineQueries();
  }

  public HashMap<String, Set<String>> createUniqueParams(Map<String, String> params) {
    HashMap<String, Set<String>> uniqueParams = new HashMap<>();
    for (Map.Entry<String, String> param : params.entrySet()) {
      String[] values = param.getValue().split(",");
      Set<String> valuesSet = new HashSet<>(Arrays.asList(values));
      uniqueParams.put(param.getKey().toLowerCase(), valuesSet);
    }

    return uniqueParams;
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
        generateMultiValueQuery(key);
        break;
    }
  }

  private void generateMultiValueQuery(String key) {
    String[] formattedValues = this.formatParamValues(uniqueParams.get(key));
    String queryString = String.format("p.%s IN (%s)", key, String.join(", ", formattedValues));
    this.queryList.add(queryString);
  }

  private void generateColorQuery(String key) {
    String[] formattedColorValues = this.formatColorParamValues(this.uniqueParams.get(key));
    String colorStringValue = String.join(", ", formattedColorValues);
    queryList.add(
        String.format("(p.primaryColorCode IN (%s) OR p.secondaryColorCode IN (%s))",
            colorStringValue, colorStringValue));
  }

  private void generatePriceQuery(String key) {
    String[] formattedPriceValues = this.formatPriceValues(this.uniqueParams.get(key));
    String priceStringValue = String.join(", ", formattedPriceValues);
    queryList.add(
        String.format("p.price IN (%s)", priceStringValue)
    );
  }

  private void generateMinMaxPriceQuery(String key) {
    String[] formattedPriceValues = this.formatPriceValues(this.uniqueParams.get(key));
    if (key.equals("min")) {
      queryList.add(
          String.format("(p.price >= %s)", formattedPriceValues[0])
      );
    } else {
      queryList.add(
          String.format("(p.price <= %s)", formattedPriceValues[0])
      );
    }
  }


  private String[] formatParamValues(Set<String> paramValues) {
    String[] formattedValues = new String[paramValues.size()];
    return paramValues.stream().map(
            value -> "'" + value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase() + "'")
        .collect(Collectors.toList())
        .toArray(formattedValues);
  }

  private String[] formatColorParamValues(Set<String> colorValues) {
    String[] formattedColorValues = new String[colorValues.size()];
    return colorValues.stream().map(value -> "'#" + value + "'").collect(Collectors.toList())
        .toArray(formattedColorValues);
  }

  private String[] formatPriceValues(Set<String> priceValues) {
    Double[] priceValuesDoubles = new Double[priceValues.size()];
    String[] formattedPriceValues = new String[priceValues.size()];
    priceValues.stream().map(value -> Double.parseDouble(value.replace("$", "")))
        .collect(Collectors.toList()).toArray(priceValuesDoubles);

    return Arrays.stream(priceValuesDoubles).map(value -> String.valueOf(value))
        .collect(Collectors.toList())
        .toArray(formattedPriceValues);
  }

  private String combineQueries() {
    return String.format("SELECT p FROM Product p WHERE (%s)", String.join(" AND ", queryList));
  }

  public Boolean checkValidUserParamKeys() {
    for (String filter : this.currentFilters) {
      if (this.uniqueParams.containsKey(filter)) {
        return true;
      }
    }
    return false;
  }

}
