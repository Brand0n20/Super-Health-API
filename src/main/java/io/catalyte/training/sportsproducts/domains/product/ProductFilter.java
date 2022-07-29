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
      "material", "price", "priceMin", "priceMax"};

  private ArrayList<String> queryList = new ArrayList<>();

  private HashMap<String, Set<String>> uniqueParams = new HashMap<>();

  public String createFilterQuery(Map<String, String> params) {
    this.makeUniqueParams(params);
    for (String key : this.uniqueParams.keySet()) {
      this.generateQuery(key);
    }
    return this.combineQueries();
  }

  private void makeUniqueParams(Map<String, String> params) {
    for (Map.Entry<String, String> param : params.entrySet()) {
      String[] values = param.getValue().split(",");
      Set<String> valuesSet = new HashSet<>(Arrays.asList(values));
      this.uniqueParams.put(param.getKey(), valuesSet);
    }
  }

  private void generateQuery(String key) {
    switch (key) {
      case "price":
        generatePriceQuery();
        break;
      case "priceMin":
      case "priceMax":
        generateMinMaxPriceQuery();
        break;
      case "color":
        generateColorQuery();
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

  private void generateColorQuery() {
    String[] formattedColorValues = this.formatColorParamValues(this.uniqueParams.get("color"));
    String colorValue = String.join(", ", formattedColorValues);
    queryList.add(
        String.format("(p.primaryColorCode IN (%s) OR p.secondaryColorCode IN (%s))",
            colorValue, colorValue));
  }

  private void generatePriceQuery() {

  }

  private void generateMinMaxPriceQuery() {

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
