package io.catalyte.training.sportsproducts.domains.purchase;

import java.util.Comparator;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public abstract class PurchaseRepositoryImpl implements
    PurchaseRepository {

  PurchaseRepository purchaseRepository;

  @Override
  public List<Purchase> findByBillingAddressEmail(String email) {
    return purchaseRepository.findAll().stream().filter(p ->
            p.getBillingAddress().getEmail().equals(email))
        .sorted(Comparator.comparing(Purchase::getId)).collect(
            Collectors.toList());
  }
}