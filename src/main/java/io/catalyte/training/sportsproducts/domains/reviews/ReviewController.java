package io.catalyte.training.sportsproducts.domains.reviews;

import static io.catalyte.training.sportsproducts.constants.Paths.REVIEWS_PATH;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * The ReviewController exposes endpoints for review related actions.
 */

@RestController
@RequestMapping(value = REVIEWS_PATH)
public class ReviewController {

  @Autowired
  private final ReviewServiceImpl reviewService;

  public ReviewController(ReviewServiceImpl reviewService) {
    this.reviewService = reviewService;
  }

  /**
   * Retrieves reviews for a specific product id.
   *
   * @param id - Product ID
   * @return a list of reviews for the specific product with a 200 OK
   */
  @GetMapping(value = "/product/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable long id) {
    return new ResponseEntity<>(reviewService.getReviewsByProductId(id), HttpStatus.OK);
  }
}
