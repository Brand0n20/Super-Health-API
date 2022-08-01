package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;

public interface ProductFilterRepository {

  List<Product> queryFilter(String filterQuery);
}
