package com.shopping.product.service;

import com.shopping.product.model.Product;
import com.shopping.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String reference, String name, String description, BigDecimal price ) {
        List<Product> valid = productRepository.findByReferenceAndName(reference, name);
         if (valid==null){
            Product product = new Product();
            product.setDescription(description);
            product.setName(name);
            product.setPrice(price);
            product.setReference(reference);
            return productRepository.save(product);}
        return null;
    }

    public Optional<Product> getProductById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public boolean deleteProductById(long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            productRepository.delete(productOptional.get());
            return true;
        }
        return false;
    }
    public boolean updateProduct(long id, Product updatedProduct) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional != null && productOptional.isPresent()) {
            Product product = productOptional.get();
            product.getId();
            // product.builder().name(updatedProduct.getName()).description(updatedProduct.getDescription()).reference(updatedProduct.getReference()).price(updatedProduct.getPrice()).build();
            product.setReference(updatedProduct.getReference());
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setPrice(updatedProduct.getPrice());
            productRepository.save(product);
            return true;
        }
        return false;
    }

}
