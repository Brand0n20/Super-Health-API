package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import java.util.Map;

public interface FilterProductRepository {

//  String getFilterAllQuery(Map<String, String> allParams);
//
//  String getFilterColorQuery(String[] colors);

  List<Product> queryFilter(String filterQuery);

}
