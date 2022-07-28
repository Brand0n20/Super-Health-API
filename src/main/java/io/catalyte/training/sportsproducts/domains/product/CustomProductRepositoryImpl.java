package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.context.annotation.Primary;


public class CustomProductRepositoryImpl implements CustomProductRepository{

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<Product> filterByCategories(Set<String> categories) {
    Query query = entityManager.createQuery("SELECT p FROM Product p");
    List<Product> products = query.getResultList();
    return products;
  }
}
