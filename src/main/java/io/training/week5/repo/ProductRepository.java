package io.training.week5.repo;

import io.training.week5.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Nullable
  Product getProductById(long id);

  void deleteProductById(long id);

}
