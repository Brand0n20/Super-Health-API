package io.catalyte.training.sportsproducts.domains.shippingCost;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Shipping Cost Repository
 */
@Repository
public interface ShippingCostRepository extends JpaRepository<ShippingCost, Long> {

  ShippingCost findShippingCostByState(String state);
}
