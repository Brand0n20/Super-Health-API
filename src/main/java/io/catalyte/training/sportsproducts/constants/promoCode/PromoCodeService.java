package io.catalyte.training.sportsproducts.constants.promoCode;

import java.util.List;

/**
 * This interface provides an abstraction layer for the Promo Code Service
 */
public interface PromoCodeService {

  List<PromoCode> postPromo(PromoCode promoCode);

  PromoCode savePromoCode(PromoCode codeToSave);

}
