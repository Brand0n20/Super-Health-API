package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.shippingCost.ShippingCost;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class is where the shipping cost gets assigned to individual states.
 */
public class ShippingCostFactory {

  /**
   * function that takes in an array of states and territories and assigns them a shipping cost
   * {String []} usStates -- an array of states and territories returns -- a list of shipping cost
   * objects.
   */
  public List<ShippingCost> addStatesToList(String[] usStates) {
    List<ShippingCost> listOfShippingCosts = new ArrayList<>();
    for (String state : usStates) {
      if (!(Objects.equals(state, "Alaska") || Objects.equals(state, "Hawaii"))) {
        ShippingCost shippingCost = new ShippingCost(state, 5);
        listOfShippingCosts.add(shippingCost);
      } else {
        ShippingCost shippingCost = new ShippingCost(state, 10);
        listOfShippingCosts.add(shippingCost);
      }
    }
    return listOfShippingCosts;
  }
}

