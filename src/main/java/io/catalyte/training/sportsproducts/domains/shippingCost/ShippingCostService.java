package io.catalyte.training.sportsproducts.domains.shippingCost;

import java.util.List;

/**
 * This interface provides an abstraction layer for the shipping cost service.
 */
public interface ShippingCostService {

  Integer findCostByState(String state);

  List<ShippingCost> getCostAndState();
}
