package io.catalyte.training.superhealthapi.domains.product;

import java.util.List;

public interface ProductFilterRepository {

  List<Product> queryFilter(String filterQuery);
}
