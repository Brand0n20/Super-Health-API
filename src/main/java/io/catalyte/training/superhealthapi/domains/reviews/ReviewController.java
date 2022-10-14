package io.catalyte.training.superhealthapi.domains.reviews;

import static io.catalyte.training.superhealthapi.constants.Paths.REVIEWS_PATH;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  /**
   * Controller to create a review.
   *
   * @param review - The review to post.
   * @return 201 created status and the posted review object.
   */
  @PostMapping
  public  ResponseEntity<Review> saveReview(@RequestBody Review review) {
    reviewService.saveReview(review);
    return new ResponseEntity<>(review, HttpStatus.CREATED);
  }
  /**
   * Retrieves all unique product IDs.
   *
   * @return a list of product IDs that are associated with a review with a 200 OK
   */
  @GetMapping(value = "/products/id/distinct")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<List<Long>> getAllDistinctReviewProductIDs() {
    return new ResponseEntity<>(reviewService.getAllDistinctProductReviewIDs(), HttpStatus.OK);
  }
}
