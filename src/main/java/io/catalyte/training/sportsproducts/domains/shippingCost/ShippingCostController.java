package io.catalyte.training.sportsproducts.domains.shippingCost;

import static io.catalyte.training.sportsproducts.constants.Paths.SHIPPINGCOST_Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes endpoints for the shipping cost domain.
 */
@RestController
@RequestMapping(value = SHIPPINGCOST_Path)
public class ShippingCostController {

  Logger logger = LogManager.getLogger(ShippingCostController.class);

  @Autowired
  private ShippingCostService shippingCostService;

  public ShippingCostController(ShippingCostService shippingCostService) {
    this.shippingCostService = shippingCostService;
  }

  /**
   * Retrieve all shipping costs.
   * 
   * return - list of all shipping costs
   */
  @GetMapping
  public ResponseEntity getCostAndState() {
    logger.info("Request received for getCostAndState");
    return new ResponseEntity<>(shippingCostService.getCostAndState(), HttpStatus.OK);
  }

  /**
   * Retrieve shipping cost by state
   *
   * @param state
   * @return - shipping cost
   */
  @GetMapping(value = "/{state}")
  public ResponseEntity<Integer> getCostByState(@PathVariable String state) {
    logger.info("Request received for getCostByState");
    return new ResponseEntity<>(shippingCostService.findCostByState(state), HttpStatus.OK);
  }
}
