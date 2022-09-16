package io.catalyte.training.sportsproducts.domains.reviews;

import io.catalyte.training.sportsproducts.domains.purchase.PurchaseServiceImpl;
import io.catalyte.training.sportsproducts.exceptions.ServerError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides the implementation for the ReviewService interface.
 */
@Service
public class ReviewServiceImpl implements ReviewService {

  /**
   * An instance of the logger to keep track of errors
   */
  private final Logger logger = LogManager.getLogger(PurchaseServiceImpl.class);
  private final ReviewRepository reviewRepository;

  @Autowired
  public ReviewServiceImpl(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  /**
   * Gets all reviews from the database for a specific product
   *
   * @param id - Product id
   * @return List of reviews for Product
   */
  @Override
  public List<Review> getReviewsByProductId(long id) {
    try {
      return reviewRepository.findReviewsByProductId(id);
    } catch (DataAccessException e) {
      throw new ServerError(e.getMessage());
    }
  }

  /**
   * Validate the review's comment
   *
   * @param review -The review to persist in the database.
   * @return  - A response status error or "true".
   */
  private boolean isAValidComment(Review review){
    if (review.comment == null || review.comment.length() <= 2000){
      return true;
    }
    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
            "Comment must cannot exceed 2000 characters.");
  }

  /**
   * Validate the review's title.
   *
   * @param review -The review to save in the database.
   * @return - A response status error or "true".
   */
  private boolean isAValidTitle(Review review){
    if (!review.title.isBlank() && review.title.length() <= 50) {
      return true;
    }
    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
            "Title is required, must contain only alphabetic characters, and must not be empty.");
  }

  /**
  * Validate the rating range
  *
  * @param review - The review to post in the database.
  * @return - A response status error or "true".
   */
  private boolean isAValidRating(Review review){
    if (review.rating <= 5d && review.rating > 0d){
      return true;
    }
    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
            "Rating must not be empty and must be between 1 and 5.");
  }

  /**
   * Check if a valid user info was provided.
   *
   * @param review - The review to post in the database
   * @return - A response status error or "true".
   */
  private boolean isAValidUser(Review review){
    if (review.user == null){
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
              "User not provided or is invalid");
    }
      return true;
  }

  /**
   * Check if a valid date format was provided.
   *
   * @param review - The review to post in the database
   * @return - A response status error or "true".
   */
  private boolean isAValidDate(Review review) throws ResponseStatusException {
    String regex = "^[0-3]?\\d/[0-3]?\\d/(?:\\d{2})?\\d{2}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(review.postedDate);
    boolean isValid = matcher.matches();
    if (isValid) {
      return true;
    }
    throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
            "Incorrect date format");
  }

  /**
   * Check if a valid product was provided.
   *
   * @param review - The review to post in the database
   * @return - A response status error or "true".
   */
  private boolean isAValidProduct(Review review){
    if (review.product == null) {
      throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
              "Product not provided or is invalid");
    }
    return true;
  }

  /**
   * @param reviewToPost - The review to persist in the database
   * @return - The persisted review.
   */
  @Override
  public Review saveReview(Review reviewToPost) {
    try {
      boolean isAValidReview = isAValidTitle(reviewToPost) && isAValidComment(reviewToPost)
              && isAValidRating(reviewToPost) && isAValidDate(reviewToPost) &&
              isAValidUser(reviewToPost) && isAValidProduct(reviewToPost);
      if (isAValidReview) {
        reviewRepository.save(reviewToPost);
      }
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
    return reviewToPost;
  }

  @Override
  public List<Long> getAllDistinctProductReviewIDs() {
    try {
      return reviewRepository.findAllDistinctProductReviewIDs();
    } catch (DataAccessException e) {
      throw new ServerError(e.getMessage());
    }
  }
}
