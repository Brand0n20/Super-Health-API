package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.shippingCost.ShippingCost;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * This class is where the shipping cost gets assigned to individual states.
 */
public class ShippingCostFactory {

  /**
   * function that takes in an array of states and territories and assigns them a shipping cost and
   * sales tax
   *
   * @return - a list of shipping costs that contain state, cost, and tax
   */
  public List<ShippingCost> addStatesToList() {
    List<ShippingCost> listOfShippingCosts = new ArrayList<>();
    List<ShippingCost> updatedListOfShippingCost = new ArrayList<>();
    HashMap<String, Double> usTaxesMap = new HashMap<>();
    String[] usStates = {"State", "Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas",
        "California", "Colorado", "Connecticut", "Delaware", "District of Columbia",
        "Federated States of Micronesia", "Florida", "Georgia", "Guam", "Hawaii", "Idaho",
        "Illinois",
        "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Marshall Islands",
        "Maryland",
        "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska",
        "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina",
        "North Dakota", "Northern Mariana Islands", "Ohio", "Oklahoma", "Oregon", "Palau",
        "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina", "South Dakota",
        "Tennessee",
        "Texas", "Utah", "Vermont", "Virgin Island", "Virginia", "Washington", "West Virginia",
        "Wisconsin", "Wyoming"};
    usTaxesMap.put("State", 0.00);
    usTaxesMap.put("Alabama", 0.04);
    usTaxesMap.put("Alaska", 0.00);
    usTaxesMap.put("American Samoa", 0.15);
    usTaxesMap.put("Arizona", 0.056);
    usTaxesMap.put("Arkansas", .065);
    usTaxesMap.put("California", 0.0725);
    usTaxesMap.put("Connecticut", 0.0635);
    usTaxesMap.put("Colorado", 0.029);
    usTaxesMap.put("Delaware", 0.00);
    usTaxesMap.put("District of Columbia", 0.06);
    usTaxesMap.put("Federated States of Micronesia", 0.05);
    usTaxesMap.put("Florida", 0.06);
    usTaxesMap.put("Georgia", 0.04);
    usTaxesMap.put("Guam", 0.00);
    usTaxesMap.put("Hawaii", 0.04);
    usTaxesMap.put("Idaho", 0.06);
    usTaxesMap.put("Illinois", 0.0625);
    usTaxesMap.put("Indiana", 0.07);
    usTaxesMap.put("Iowa", 0.06);
    usTaxesMap.put("Kansas", 0.065);
    usTaxesMap.put("Kentucky", 0.06);
    usTaxesMap.put("Louisiana", 0.0445);
    usTaxesMap.put("Maine", 0.05);
    usTaxesMap.put("Marshall Islands", 0.03);
    usTaxesMap.put("Maryland", 0.06);
    usTaxesMap.put("Massachusetts", 0.06);
    usTaxesMap.put("Michigan", 0.06);
    usTaxesMap.put("Minnesota", 0.06875);
    usTaxesMap.put("Mississippi", 0.07);
    usTaxesMap.put("Missouri", 0.04225);
    usTaxesMap.put("Montana", 0.00);
    usTaxesMap.put("Nebraska", 0.055);
    usTaxesMap.put("Nevada", 0.685);
    usTaxesMap.put("New Hampshire", 0.00);
    usTaxesMap.put("New Jersey", 0.0625);
    usTaxesMap.put("New Mexico", 0.05);
    usTaxesMap.put("New York", 0.04);
    usTaxesMap.put("North Carolina", 0.0475);
    usTaxesMap.put("North Dakota", 0.05);
    usTaxesMap.put("Northern Mariana Islands", 0.00);
    usTaxesMap.put("Ohio", 0.0575);
    usTaxesMap.put("Oklahoma", 0.045);
    usTaxesMap.put("Oregon", 0.00);
    usTaxesMap.put("Palau", 0.00);
    usTaxesMap.put("Pennsylvania", 0.06);
    usTaxesMap.put("Puerto Rico", 0.115);
    usTaxesMap.put("Rhode Island", 0.07);
    usTaxesMap.put("South Carolina", 0.06);
    usTaxesMap.put("South Dakota", 0.045);
    usTaxesMap.put("Tennessee", 0.07);
    usTaxesMap.put("Texas", 0.0625);
    usTaxesMap.put("Utah", 0.0485);
    usTaxesMap.put("Vermont", 0.06);
    usTaxesMap.put("Virgin Island", 0.00);
    usTaxesMap.put("Virginia", 0.043);
    usTaxesMap.put("Washington", 0.065);
    usTaxesMap.put("West Virginia", 0.06);
    usTaxesMap.put("Wisconsin", 0.05);
    usTaxesMap.put("Wyoming", 0.04);
    for (String state : usStates) {
      if (!(Objects.equals(state, "Alaska") || Objects.equals(state, "Hawaii") || Objects.equals(
          state, "State"))) {
        ShippingCost shippingCost = new ShippingCost(state, 5, 0.00);
        listOfShippingCosts.add(shippingCost);
      } else if (Objects.equals(state, "State")) {
        ShippingCost shippingCost = new ShippingCost(state, 0, 0.00);
        listOfShippingCosts.add(shippingCost);
      } else {
        ShippingCost shippingCost = new ShippingCost(state, 10, 0.00);
        listOfShippingCosts.add(shippingCost);
      }

    }
    for (ShippingCost shippingCost : listOfShippingCosts) {
      for (Map.Entry<String, Double> usTaxes : usTaxesMap.entrySet()) {
        if (Objects.equals(usTaxes.getKey(), shippingCost.getState())) {
          ShippingCost shippingCostUpdated = new ShippingCost(shippingCost.getState(),
              shippingCost.getCost(), usTaxes.getValue());
          updatedListOfShippingCost.add(shippingCostUpdated);
        }
      }
    }
    return updatedListOfShippingCost;
  }
}



