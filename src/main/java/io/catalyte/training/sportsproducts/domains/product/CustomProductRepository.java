package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import java.util.Set;

public interface CustomProductRepository {
    List<Product> filterByCategories(Set<String> categories);
}
