package com.shopping.product.api;

import com.shopping.product.model.Product;
import com.shopping.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

     private final ProductRepository productRepository;

     @Autowired
     public ProductController(ProductRepository productRepository) {
          this.productRepository = productRepository;
     }
     @GetMapping
     public ResponseEntity<List<Product>> getAllProducts() {
          List<Product> products = productRepository.findAll();
          return ResponseEntity.ok(products);
     }

     @GetMapping("/{id}")
     public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
          return productRepository.findById(id)
                  .map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
     }

     @PostMapping
     public ResponseEntity<Product> createProduct(@RequestBody Product product) {
          Product createdProduct = productRepository.save(product);
          return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
     }

     @PutMapping("/{id}")
     public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
          if (productRepository.existsById(id)) {
               //product.fi setId(id);
               Product updatedProduct = productRepository.save(product);
               return ResponseEntity.ok(updatedProduct);
          } else {
               return ResponseEntity.notFound().build();
          }
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
          if (productRepository.existsById(id)) {
               productRepository.deleteById(id);
               return ResponseEntity.noContent().build();
          } else {
               return ResponseEntity.notFound().build();
          }
     }
}
