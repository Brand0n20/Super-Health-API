package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Product repository
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductFilterRepository {

  @Query("SELECT DISTINCT p.type FROM Product p WHERE p.active = TRUE")
  List<String> findByType();

  @Query("SELECT DISTINCT p.category FROM Product p WHERE p.active = TRUE")
  List<String> findByCategory();

  @Query("SELECT DISTINCT p.brand FROM Product p WHERE p.active = TRUE")
  List<String> findByBrand();

  @Query("SELECT DISTINCT p.demographic FROM Product p WHERE p.active = TRUE")
  List<String> findByDemographic();

  @Query("SELECT DISTINCT p.material FROM Product p WHERE p.active = TRUE")
  List<String> findByMaterial();

  @Query("SELECT DISTINCT p.primaryColorCode FROM Product p WHERE p.active = TRUE")
  List<String> findByPrimaryColor();

  @Query("SELECT DISTINCT p.secondaryColorCode FROM Product p WHERE p.active = TRUE")
  List<String> findBySecondaryColor();

}
