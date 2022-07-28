package io.catalyte.training.sportsproducts.domains.product;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CustomProductRepositoryImpl implements CustomProductRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Product> filterAllParameters(Map<String, String> allParams) {

    HashMap<String, Set<String>> allParamsMapSet = new HashMap<>();

    for (Map.Entry<String, String> param : allParams.entrySet()) {
      String[] values = param.getValue().split(",");
      Set<String> valuesSet = new HashSet<>(Arrays.asList(values));
      allParamsMapSet.put(param.getKey(), valuesSet);
    }

    System.out.println("CustomProductRepositoryImpl");





    Query query = entityManager.createQuery(
        "SELECT p FROM Product p WHERE (p.category IN ('Soccer')) AND (p.demographic IN ('Men'))");
    List<Product> products = query.getResultList();
    return products;
  }
}
