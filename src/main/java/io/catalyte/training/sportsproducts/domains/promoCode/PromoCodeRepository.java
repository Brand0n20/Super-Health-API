package io.catalyte.training.sportsproducts.domains.promoCode;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Promo Code Repository
 */
@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {
  Optional<PromoCode> findByTitle(String title);
}
