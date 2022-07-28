package io.catalyte.training.sportsproducts.domains.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductFilter {

  private ArrayList<String> multiValueQueryList = new ArrayList<>();
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
      default:
        generateMultiValueQuery(key);
    }
  }

  private void generateMultiValueQuery(String key) {
    String[] formattedValues = this.formatParamValues(uniqueParams.get(key));
    String queryString = String.format("p.%s IN (%s)", key, String.join(", ", formattedValues));
    this.multiValueQueryList.add(queryString);
  }

  private String[] formatParamValues(Set<String> paramValues) {
    String[] formattedValues = new String[paramValues.size()];
    return paramValues.stream().map(value -> "'" + value + "'").collect(Collectors.toList())
        .toArray(formattedValues);
  }

  private String combineQueries() {
    return String.format("SELECT p FROM Product p WHERE (%s)",
        String.join(" AND ", multiValueQueryList));
  }
}
