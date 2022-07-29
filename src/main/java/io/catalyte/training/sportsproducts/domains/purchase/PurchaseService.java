package io.catalyte.training.sportsproducts.domains.purchase;

import java.util.List;

public interface PurchaseService {

  Purchase savePurchase(Purchase purchaseToSave);

  List<Purchase> findAllPurchases();

  Purchase getPurchaseById(long id);

  List<Purchase> findPurchasesByEmail(String email);
}
