package io.catalyte.training.sportsproducts.domains.purchase;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {

  List<LineItem> findLineItemsByProductId(long productId);
}
