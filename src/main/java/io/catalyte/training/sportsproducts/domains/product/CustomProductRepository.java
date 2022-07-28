package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CustomProductRepository {

  List<Product> filterAllParameters(Map<String, String> allParams);
}
