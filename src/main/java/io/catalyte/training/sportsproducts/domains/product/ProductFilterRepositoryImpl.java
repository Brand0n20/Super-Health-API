package io.catalyte.training.sportsproducts.domains.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Custom filter repository to filter products in the database
 */

public class ProductFilterRepositoryImpl implements ProductFilterRepository {

  @PersistenceContext
  private EntityManager entityManager;

  /**
   * Queries the database with a custom valid product query string
   *
   * @param filterQuery
   * @return - List of Products based on the filter query
   */
  @Override
  public List<Product> queryFilter(String filterQuery) {
    if (filterQuery == null) {
      return Collections.emptyList();
    }

    Query query = entityManager.createQuery(filterQuery);
    return query.getResultList();
  }
}