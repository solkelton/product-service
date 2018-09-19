package io.training.week5.controller;

import io.training.week5.model.Product;
import io.training.week5.service.ProductService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping
  public Product addProduct(@RequestBody Product product) {
    return productService.addProduct(product);
  }

  @PutMapping("/{productId}")
  public Product updateProduct(@PathVariable("productId") long productId, @RequestBody Product product) {
    return productService.updateProduct(productId, product);
  }

  @Transactional
  @DeleteMapping("/{productId}")
  public boolean removeProduct(@PathVariable("productId") long productId) {
    return productService.removeProduct(productId);
  }

}
