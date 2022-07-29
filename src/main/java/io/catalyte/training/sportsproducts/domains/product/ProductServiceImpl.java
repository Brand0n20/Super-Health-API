package io.catalyte.training.sportsproducts.domains.product;

import io.catalyte.training.sportsproducts.exceptions.ResourceNotFound;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * This class provides the implementation for the ProductService interface.
 */
@Service
public class ProductServiceImpl implements ProductService {

  private final Logger logger = LogManager.getLogger(ProductServiceImpl.class);


  ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  /**
   * Retrieves all products from the database, optionally making use of an example if it is passed.
   *
   * @param product - an example product to use for querying
   * @return - a list of products matching the example, or all products if no example was passed
   */
  @Override
  public List<Product> getProducts(Product product, Map<String, String> allParams) {
    try {
      ProductFilter filter = new ProductFilter();
      productRepository.findAll();
      return productRepository.queryFilter(filter.createFilterQuery(allParams));

    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  /**
   * Retrieves the product with the provided id from the database.
   *
   * @param id - the id of the product to retrieve
   * @return - the product
   */
  @Override
  public Product getProductById(Long id) {
    Product product;

    try {
      product = productRepository.findById(id).orElse(null);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    if (product != null) {
      return product;
    } else {
      logger.info("Get by id failed, it does not exist in the database: " + id);
      throw new ResourceNotFound("Get by id failed, it does not exist in the database: " + id);
    }
  }

  /**
   * Retrieves all unique types within the database.
   *
   * @return A list of strings with unique type.
   */
  @Override
  public List<String> getUniqueTypes() {
    List<String> types;

    try {
      types = productRepository.findByType();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    return types;
  }

  /**
   * Retrieves all the unique categories within the databases.
   *
   * @return A list of strings with unique categories.
   */
  @Override
  public List<String> getUniqueCategories() {
    List<String> categories;

    try {
      categories = productRepository.findByCategory();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    return categories;
  }

}
