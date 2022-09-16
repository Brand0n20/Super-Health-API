package io.catalyte.training.sportsproducts.domains.reviews;

import java.util.List;

/**
 * This interface provides an abstraction layer for the Review Service
 */
public interface ReviewService {

  List<Review> getReviewsByProductId(long id);

  Review saveReview(Review reviewToPost);

  List<Long> getAllDistinctProductReviewIDs();
}
