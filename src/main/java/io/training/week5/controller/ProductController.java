package io.training.week5.controller;

import io.training.week5.model.Product;
import io.training.week5.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ProductController {
  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping("/{productId}")
  public Product retrieveProduct(@PathVariable("productId") long productId) {
    return productService.retrieveProduct(productId);
  }
}
