package io.catalyte.training.superhealthapi.constants.promoCode;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Promo Code Repository
 */
@Repository
public interface PromoCodeRepository extends JpaRepository<PromoCode, Long> {

  Optional<PromoCode> findByTitle(String title);
}
