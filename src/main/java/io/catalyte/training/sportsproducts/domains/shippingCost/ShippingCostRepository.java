package io.catalyte.training.sportsproducts.domains.shippingCost;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Shipping Cost Repository
 */
@Repository
public interface ShippingCostRepository extends JpaRepository<ShippingCost, Long> {

  @Query("SELECT DISTINCT p.state FROM ShippingCost p")
  List<String> getByStates();

  Integer findCostByState(String state);
}
