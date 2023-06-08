package com.shoppyng.product.api;

/*import com.shoppyng.cart.model.ShoppingCart;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;*/
import com.shoppyng.product.model.Product;
import com.shoppyng.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
public class ProductController {


@Autowired
private ProductService productService;

     @GetMapping("/products/{id}")
     public ResponseEntity<Product> getProduct(@PathVariable("id") String id){

          return productService.getProduct(id).map(product -> ResponseEntity.ok(product)).orElse(ResponseEntity.notFound().build());
     }
     @PostMapping
     public ResponseEntity<Product> createProduct(@RequestBody Product product) {
          Product createdProduct = productService.createProduct(product.getName(), product.getReference(), product.getDescription(), product.getPrice());
          return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
     }

     @PutMapping("/{id}")
     public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
          boolean updated = productService.updateProduct(id, product);

          if (updated) {
               return ResponseEntity.ok(product);
          } else {
               return ResponseEntity.notFound().build();
          }
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
          boolean deleted = productService.deleteProduct(id);

          if (deleted) {
               return ResponseEntity.noContent().build();
          } else {
               return ResponseEntity.notFound().build();
          }
     }

}