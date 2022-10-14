package io.catalyte.training.superhealthapi.domains.product;

import java.util.List;
import java.util.Map;

/**
 * This interface provides an abstraction layer for the Products Service
 */
public interface ProductService {

  List<Product> getProducts(Map<String, String> allParams);

  Product getProductById(Long id);

  Product saveProduct(Product productToSave);

  Product updateProduct(Product productToUpdate);

  void deleteProduct(Product productToDelete);

  List<String> getUniqueTypes();

  List<String> getUniqueCategories();

  List<String> getUniqueDemographics();

  List<String> getUniqueBrands();

  List<String> getUniqueMaterials();

  List<String> getUniqueColors();
}