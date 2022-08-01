package io.catalyte.training.sportsproducts.domains.product;


import static io.catalyte.training.sportsproducts.constants.Paths.PRODUCTS_PATH;

import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * The ProductController exposes endpoints for product related actions.
 */
@RestController
@RequestMapping(value = PRODUCTS_PATH)
public class ProductController {

  Logger logger = LogManager.getLogger(ProductController.class);

  @Autowired
  private ProductService productService;

  /**
   * Controller method for getting all products in the database, optionally can filter products
   * returned with a valid filter
   *
   * @param product
   * @return - a list of products in database and a 200 status
   */
  @GetMapping
  public ResponseEntity<List<Product>> getProducts(Product product,
      @RequestParam(required = false) Map<String, String> allParams) {
    logger.info("Request received for getProducts");
    return new ResponseEntity<>(productService.getProducts(product, allParams), HttpStatus.OK);

  }


  /**
   * Controller method for getting a product from database by id
   *
   * @param id - id to get product by
   * @return - a product by id and a 200 status
   */

  @GetMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    logger.info("Request received for getProductsById: " + id);
    return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
  }

  /**
   * Controller method for getting a list of types from database
   *
   * @return - a list of strings with the unique types and a 200 status
   */
  @GetMapping(value = "/types")
  public ResponseEntity<List<String>> getUniqueType() {
    logger.info("Request received for getUniqueTypes");

    return new ResponseEntity<>(productService.getUniqueTypes(), HttpStatus.OK);
  }

  /**
   * Controller method for getting a list of categories from database
   *
   * @return - a list of strings with the unique categories and a 200 status
   */
  @GetMapping(value = "/categories")
  public ResponseEntity<List<String>> getUniqueCategory() {
    logger.info("Request received for getUniqueCategory");

    return new ResponseEntity<>(productService.getUniqueCategories(), HttpStatus.OK);
  }

}
