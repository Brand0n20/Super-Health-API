package io.catalyte.training.sportsproducts.domains.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

  List<Purchase> findByBillingAddressEmail(String email);
  List<Purchase> findByCreditCard(String creditCard);

   Purchase findPurchaseById(final long id);


}
