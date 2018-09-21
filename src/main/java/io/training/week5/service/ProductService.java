package io.training.week5.service;

import io.training.week5.model.Product;
import io.training.week5.repo.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());
  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Product retrieveProduct(long id) {
    Product product = productRepository.getProductById(id);
    if(validateProduct(product)) {
      logger.info("Valid Product Found at retrieveProduct");
     return product;
    }
    logger.debug("Invalid Shipment Id At retrieveProduct");
    logger.debug("Id: {}", id);
    return new Product();
  }

  public Product addProduct(Product product) {
    if(validateProduct(product)) {
      logger.info("Valid Product Added");
      return productRepository.save(product);
    }
    logger.debug("Invalid Product Attempted to Add");
    logger.debug("Product: {}", product.toString());
    return new Product();
  }

  public boolean removeProduct(long id) {
    Product product = retrieveProduct(id);
    if(validateProduct(product)) {
      logger.info("Valid Product Removed");
      productRepository.deleteProductById(id);
      return true;
    }
    logger.debug("Invalid Product Attempted to Remove");
    logger.debug("Product Id: {}", id);
    return false;
  }

  public Product updateProduct(long productId, Product updatedProduct) {
    Product originalProduct = retrieveProduct(productId);
    if(validateProduct(originalProduct)) {
      logger.info("Valid Product updated");
      Product newProduct = update(originalProduct, updatedProduct);
      return productRepository.save(newProduct);
    }
    logger.debug("Invalid Product Attempted to Update at Product Id {}", productId);
    logger.debug("Product: {}", updatedProduct.toString());
    return new Product();
  }

  private boolean validateProduct(Product product) {
    if(product == null) return false;
    if(product.getDescription() == null) return false;
    if(product.getImage() == null) return false;
    if(product.getName() == null) return false;
    if(product.getPrice() == 0) return false;
    return true;
  }

  private Product update(Product original, Product updated) {
    Product newProduct = new Product();
    newProduct.setId(original.getId());

    if(updated == null) return original;

    if(updated.getDescription() == null) newProduct.setDescription(original.getDescription());
    else newProduct.setDescription(updated.getDescription());

    if(updated.getImage() == null) newProduct.setImage(original.getImage());
    else newProduct.setImage(updated.getImage());

    if(updated.getName() == null) newProduct.setName(original.getName());
    else newProduct.setName(updated.getName());

    if(updated.getPrice() == 0) newProduct.setPrice(original.getPrice());
    else newProduct.setPrice(updated.getPrice());

    return newProduct;
  }

}
