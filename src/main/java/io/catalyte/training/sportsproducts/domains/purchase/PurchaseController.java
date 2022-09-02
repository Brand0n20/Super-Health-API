package io.catalyte.training.sportsproducts.domains.purchase;

import static io.catalyte.training.sportsproducts.constants.Paths.PURCHASES_PATH;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Exposes endpoints for the purchase domain
 */
@RestController
@RequestMapping(value = PURCHASES_PATH)
public class PurchaseController {

  private final PurchaseService purchaseService;
  Logger logger = LogManager.getLogger(PurchaseController.class);

  @Autowired
  public PurchaseController(PurchaseService purchaseService) {
    this.purchaseService = purchaseService;
  }

  /**
   * Controller method for creating new purchase
   *
   * @param purchase - new purchase
   * @return 201 created status
   */
  @PostMapping
  public ResponseEntity<Purchase> savePurchase(@RequestBody Purchase purchase) {

    purchaseService.savePurchase(purchase);

    return new ResponseEntity<>(purchase, HttpStatus.CREATED);
  }

  /**
   * Controller method for getting purchases linked to a billingAddress email
   *
   * @param email - email address in interest
   * @return a list of purchases linked to the email and 202 status code
   */
  @GetMapping(value = "/email/{email}")
  public ResponseEntity findAllPurchasesByEmail(
      @PathVariable(name = "email", required = false) String email) {
    return new ResponseEntity<>(purchaseService.findPurchasesByEmail(email), HttpStatus.OK);
  }

  /**
   * Controller method for getting purchases linked to a users email address
   *
   * @param email - Users email address
   * @return a list of purchases linked to the email and 202 status code
   */
  @GetMapping(value = "/{email}")
  public ResponseEntity findUserPurchasesByEmail(
      @PathVariable(name = "email", required = false) String email) {
      return new ResponseEntity<>(purchaseService.findUserPurchasesByEmail(email), HttpStatus.OK);
  }

  /**
   * Controller method for returning not found when no email address is provided
   *
   * @return 404 status code
   */
  @GetMapping
  public ResponseEntity findAllPurchasesByEmail() {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}



