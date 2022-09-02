package io.catalyte.training.sportsproducts.domains.promoCode;

import io.catalyte.training.sportsproducts.exceptions.ServerError;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * Implements PromoCodeService interface
 */
@Service
public class PromoCodeServiceImpl implements PromoCodeService {

  private final Logger logger = LogManager.getLogger(PromoCodeServiceImpl.class);
  PromoCodeRepository promoRepository;
  PromoCodeValidation promoCodeValidation = new PromoCodeValidation();

  @Autowired
  public PromoCodeServiceImpl(PromoCodeRepository promoRepository) {
    this.promoRepository = promoRepository;
  }

  /**
   * Will find all the promo codes created and return them
   *
   * @param promoCode - code that will be posted
   * @return promoCode - object created
   */
  public List<PromoCode> postPromo(PromoCode promoCode) {
    try {
      return promoRepository.findAll();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServerError(e.getMessage());
    }
  }

  /**
   * Will try the validity methods, and if they pass, then the object is saved to the database
   *
   * @param newPromoCode - code that will be saved
   * @return newPromoCode - newly saved object
   */
  public PromoCode savePromoCode(PromoCode newPromoCode) {
    try {
      promoCodeValidation.codeValidation(newPromoCode);  // checks if the title is valid
      PromoCode existingPromoCode = promoRepository.findByTitle(newPromoCode.getTitle())
          .orElse(null);
      if (existingPromoCode == null) {
        return promoRepository.save(newPromoCode);
      }

    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    return newPromoCode;
  }

}
