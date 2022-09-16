package io.catalyte.training.sportsproducts.domains.shippingCost;

import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * This class provides implementation for the shipping cost service interface.
 */
@Service
public class ShippingCostImpl implements ShippingCostService {

  private final Logger logger = LogManager.getLogger(ShippingCostImpl.class);

  ShippingCostRepository shippingCostRepository;

  @Autowired
  public ShippingCostImpl(ShippingCostRepository shippingCostRepository) {
    this.shippingCostRepository = shippingCostRepository;
  }

  /**
   * Retrieves all shipping costs from the database.
   *
   * @return - list of shipping costs
   */
  public List<ShippingCost> getCostAndState() {
    List<ShippingCost> shippingCost;

    try {
      shippingCost = shippingCostRepository.findAll();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }

    return shippingCost;
  }

  /**
   * Retrieves a shipping cost object by state from the database.
   *
   * @return - a shipping cost object
   */
  @Override
  public ShippingCost getShippingCostObjectByState(String state) {
    List<ShippingCost> shippingCostList = shippingCostRepository.findAll();
    ShippingCost shippingCost = null;

    try {
      for (ShippingCost object : shippingCostList) {
        if (Objects.equals(object.getState(), state)) {
          shippingCost = object;
        }
      }
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
    System.out.println(shippingCost);
    return shippingCost;
  }

}
