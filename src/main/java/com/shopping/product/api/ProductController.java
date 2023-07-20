package com.shopping.product.api;

import com.shopping.product.model.Product;
import com.shopping.product.repository.ProductRepository;
import com.shopping.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

     @Autowired
     ProductService productService;

      @GetMapping
     public ResponseEntity<List<Product>> getAllProducts() {
          List<Product> products = productService.getAllProducts();
          return ResponseEntity.ok(products);
     }

     @GetMapping("/{id}")
     public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id) {
          return productService.getProductById(id)
                  .map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
     }

     @PostMapping
     public ResponseEntity<Product> createProduct(@RequestBody Product product) {
          Product createdProduct = productService.createProduct(product.getReference(), product.getName(), product.getDescription(), product.getPrice());
          return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
     }

     @PutMapping("/{id}")
     public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
          if (productService.getProductById(id).isPresent()) {
               //product.fi setId(id);
               productService.updateProduct(id,product);
               return ResponseEntity.ok(product);
          } else {
               return ResponseEntity.notFound().build();
          }
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteProduct(@PathVariable("id") Integer id) {
          if (productService.getProductById(id).isPresent()) {
               productService.deleteProductById(id);
               return ResponseEntity.noContent().build();
          } else {
               return ResponseEntity.notFound().build();
          }
     }
}
