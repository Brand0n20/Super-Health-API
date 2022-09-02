package io.catalyte.training.sportsproducts.domains.reviews;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

  List<Review> findReviewsByProductId(long productId);
}
