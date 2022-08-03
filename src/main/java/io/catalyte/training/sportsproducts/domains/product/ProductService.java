package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import java.util.Map;

/**
 * This interface provides an abstraction layer for the Products Service
 */
public interface ProductService {

  List<Product> getProducts(Map<String, String> categories);

  Product getProductById(Long id);

  List<String> getUniqueTypes();

  List<String> getUniqueCategories();
}

