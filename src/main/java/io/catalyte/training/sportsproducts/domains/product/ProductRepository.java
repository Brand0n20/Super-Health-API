package io.catalyte.training.sportsproducts.domains.product;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("SELECT category FROM Product p WHERE p.category IN :categories")
  List<Product> getProductsByCategory(@Param("categories") Collection<String> categories);
}
