package io.catalyte.training.sportsproducts.domains.product;

import io.catalyte.training.sportsproducts.exceptions.ResourceNotFound;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
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

  ProductValidation productValidation = new ProductValidation();

  /**
   * Retrieves all products from the database, optionally making use of params if passed.
   *
   * @return - a list of products matching the example, or all products if no example was passed
   */
  @Override
  public List<Product> getProducts(Map<String, String> allParams) {

    try {
      if (allParams.isEmpty() || allParams == null) {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
      }

      for (Map.Entry<String, String> param : allParams.entrySet()) {
        if (param.getKey().equals("") || param.getValue().equals("")) {
          return Collections.emptyList();
        }
      }

      ProductFilter filter = new ProductFilter();
      filter.createUniqueParams(allParams);

      if (!filter.validParams()) {
        return Collections.emptyList();
      }

      return productRepository.queryFilter(filter.createFilterQuery());

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
      logger.info("Product with id " + id + " does not exist in the database");
      throw new ResourceNotFound("Product with id " + id + " does not exist in the database");
    }
  }

  /**
   * Persists a purchase to the database
   *
   * @param newProduct - the purchase to persist
   * @return the persisted purchase with ids
   */

  @Override
  public Product saveProduct(Product newProduct) {
    try {
      if (productValidation.validProduct(newProduct)) {
        productRepository.save(newProduct);
      }
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
    return newProduct;
  }

  public Product updateProduct(Product updatedProduct) {
    Product productToBeUpdated;
    try {
      productValidation.validProduct(updatedProduct);
      productToBeUpdated = productRepository.findById(updatedProduct.getId()).orElse(null);

    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    if (productToBeUpdated == null) {
      logger.error("Product with id: " + updatedProduct.getId() + " does not exist");
      throw new ResourceNotFound("Product with id: " + updatedProduct.getId() + " does not exist");
    }

    if (productToBeUpdated.getId() == null) {
      productToBeUpdated.setId(updatedProduct.getId());
    }

    try {
      logger.info("Updated product " + updatedProduct.getId());
      return productRepository.save(updatedProduct);
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  @Override
  public void deleteProduct(Product productToDelete) {
    try {
      logger.info("Deleted product " + productToDelete.getId());
      productRepository.deleteById(productToDelete.getId());
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getLocalizedMessage());
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

  @Override
  public List<String> getUniqueDemographics() {
    List<String> demographics;

    try {
      demographics = productRepository.findByDemographic();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    return demographics;
  }

  @Override
  public List<String> getUniqueBrands() {
    List<String> brands;

    try {
      brands = productRepository.findByBrand();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    return brands;
  }

  @Override
  public List<String> getUniqueMaterials() {
    List<String> materials;

    try {
      materials = productRepository.findByMaterial();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    return materials;
  }

  @Override
  public List<String> getUniqueColors() {
    List<String> colors1;
    List<String> colors2;
    List<String> colors;

    try {
      colors1 = productRepository.findByPrimaryColor();
      colors2 = productRepository.findBySecondaryColor();
      colors1.addAll(colors2);
      colors = colors1.stream().distinct().collect(Collectors.toList());
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    return colors;
  }
}
