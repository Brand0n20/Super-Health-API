package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import java.util.Map;

public interface FilterProductRepository {
  List<Product> queryFilter(String filterQuery);
}
