package io.catalyte.training.sportsproducts.domains.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class FilterProductRepositoryImpl implements FilterProductRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Product> filterAllParameters(Map<String, String> allParams) {

    ArrayList<String> queryList = new ArrayList<>();
    HashMap<String, Set<String>> allParamsMapSet = new HashMap<>();

    for (Map.Entry<String, String> param : allParams.entrySet()) {
      String[] values = param.getValue().split(",");
      Set<String> valuesSet = new HashSet<>(Arrays.asList(values));
      allParamsMapSet.put(param.getKey(), valuesSet);
    }

    for (Map.Entry<String, Set<String>> param : allParamsMapSet.entrySet()) {
      String[] values = new String[param.getValue().size()];
      param.getValue().stream().map(value -> "'" + value + "'").collect(Collectors.toList())
          .toArray(values);
      String queryString = String.format("p.%s IN (%s)", param.getKey(), String.join(", ", values));
      queryList.add(queryString);
    }

    String queryString = String.format("SELECT p FROM Product p WHERE (%s)",
        String.join(" AND ", queryList));

    Query query = entityManager.createQuery(
        queryString);
    List<Product> products = query.getResultList();
    return products;
  }



}
