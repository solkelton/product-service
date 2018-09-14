package io.training.week5.controller;

import io.training.week5.model.Product;
import io.training.week5.service.ProductService;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{id}")
  public Optional<Product> retrieveProduct(@PathVariable("id") long id) {
    return productService.retrieveProduct(id);
  }


}
