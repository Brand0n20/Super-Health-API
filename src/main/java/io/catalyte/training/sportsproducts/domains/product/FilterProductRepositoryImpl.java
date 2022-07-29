package io.catalyte.training.sportsproducts.domains.product;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class FilterProductRepositoryImpl implements FilterProductRepository {

  @PersistenceContext
  private EntityManager entityManager;

  public List<Product> queryFilter(String filterQuery) {
    if (filterQuery == null) {
      return new ArrayList<>();
    }

    Query query = entityManager.createQuery(filterQuery);
    return query.getResultList();
  }
}
