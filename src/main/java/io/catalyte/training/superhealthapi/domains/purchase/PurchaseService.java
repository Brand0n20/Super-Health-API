package io.catalyte.training.superhealthapi.domains.purchase;

import io.catalyte.training.superhealthapi.responseObjects.UserPurchase;
import java.util.List;

/**
 * Interface for the purchase services
 */
public interface PurchaseService {

  /**
   * Interface that saves a purchase to the Database
   *
   * @param purchaseToSave
   * @return
   */
  Purchase savePurchase(Purchase purchaseToSave);

  /**
   * Finds purchases by billing address email
   *
   * @param email
   * @return
   */
  List<Purchase> findPurchasesByEmail(String email);

  /**
   * Interface for finding purchases by a user email
   *
   * @param email - The user email
   * @return - A list of all purchases associated with the given user email
   * @author - Andrew Salerno
   */
  List<UserPurchase> findUserPurchasesByEmail(String email);

  List<LineItem> findPurchasesByProductId(long productId);
}
