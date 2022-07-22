package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;

/**
 * This interface provides an abstraction layer for the Products Service
 */
public interface ProductService {

  List<Product> getProducts(Product product);

  Product getProductById(Long id);

  Product getProductByType(String type);

  Product getProductByCategory(String category);
}
