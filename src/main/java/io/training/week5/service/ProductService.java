package io.training.week5.service;

import io.training.week5.model.Product;
import io.training.week5.repo.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product retrieveProduct(long id) {
    return productRepository.getProductById(id);
  }
}
