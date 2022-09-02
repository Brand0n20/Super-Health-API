package io.catalyte.training.sportsproducts.domains.reviews;

import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * This class provides the implementation for the ReviewService interface.
 */
@Service
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;

  @Autowired
  public ReviewServiceImpl(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository;
  }

  /**
   * Gets all reviews from the database for a specific product
   *
   * @param id - Product Id
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
}
