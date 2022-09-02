package io.catalyte.training.sportsproducts.domains.purchase;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for purchases
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

  /**
   * Interface for finding purchases by billing address email
   *
   * @param email - The billing address of a purchase
   * @return - A list of all purchases having a billing address email that matches the given email
   */
  List<Purchase> findByBillingAddressEmail(String email);

  /**
   * Interface for finding a users purchases by email
   *
   * @param email - A users email
   * @return - A users purchase history data
   */
  List<Purchase> findByUserEmail(String email);
}
