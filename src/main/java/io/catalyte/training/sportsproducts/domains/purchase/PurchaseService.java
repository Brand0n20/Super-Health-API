package io.catalyte.training.sportsproducts.domains.purchase;

import io.catalyte.training.sportsproducts.responseObjects.UserPurchase;
import java.util.List;

/**
 * Interface for the purchase services
 */
public interface PurchaseService {

  Purchase savePurchase(Purchase purchaseToSave);

  List<Purchase> findPurchasesByEmail(String email);

  List<UserPurchase> findUserPurchasesByEmail(String email);
}
