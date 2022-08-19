package io.catalyte.training.sportsproducts.domains.product;

import io.catalyte.training.sportsproducts.domains.purchase.Purchase;
import java.util.List;
import java.util.Map;

/**
 * This interface provides an abstraction layer for the Products Service
 */
public interface ProductService {

  List<Product> getProducts(Map<String, String> allParams);

  Product getProductById(Long id);

  Product saveProduct(Product productToSave);

  List<String> getUniqueTypes();

  List<String> getUniqueCategories();

}

