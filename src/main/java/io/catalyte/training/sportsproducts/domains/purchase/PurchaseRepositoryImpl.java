package io.catalyte.training.sportsproducts.domains.purchase;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 * An implementation of the purchase repository
 */
@Repository
public abstract class PurchaseRepositoryImpl implements
    PurchaseRepository {

  /**
   * An instance of the purchase repository
   */
  private PurchaseRepository purchaseRepository;


  private Purchase p;

  /**
   * Gets all purchases in a purchase repository that have a give billing address
   *
   * @param email - The billing address of a purchase
   * @return - All purchases that have the given billing address email
   */
  @Override
  public List<Purchase> findByBillingAddressEmail(String email) {
    return purchaseRepository.findAll().stream()
        .filter(p -> p.getBillingAddress().getEmail().equals(email))
        .sorted(Comparator.comparing(Purchase::getId)).collect(Collectors.toList());
  }

  /**
   * Gets all purchases with a given user email address
   *
   * @param email - A users email address
   * @return - A list of UserPurchase response objects
   * @author - Andrew Salerno
   */
  @Override
  public List<Purchase> findByUserEmail(String email) {
    return purchaseRepository.findAll().stream().filter(p -> p.getUserEmail().equals(email))
        .sorted(Comparator.comparing(Purchase::getId)).collect(Collectors.toList());
  }
}