package io.catalyte.training.sportsproducts.constants.promoCode;

import static io.catalyte.training.sportsproducts.constants.Paths.PROMO_CODE;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for promo_code entity
 */
@RestController
@RequestMapping(value = PROMO_CODE)
public class PromoCodeController {

  Logger logger = LogManager.getLogger(PromoCodeController.class);

  private final PromoCodeService promoCodeService;  // references the actual service

  @Autowired
  public PromoCodeController(PromoCodeService promoCodeService) {
    this.promoCodeService = promoCodeService;
  }

  /**
   * Controller method for posting a promo code
   *
   * @param promoCode - code to be entered
   * @return PromoCode
   */
  @PostMapping
  public ResponseEntity<PromoCode> postPromo(@RequestBody PromoCode promoCode) {
    logger.info("Request received for postPromo");
    promoCodeService.savePromoCode(promoCode);
    return new ResponseEntity<>(promoCode,
        HttpStatus.CREATED);   // ResponseEntity represents HTTP response, including headers, body, and status
  }
}
