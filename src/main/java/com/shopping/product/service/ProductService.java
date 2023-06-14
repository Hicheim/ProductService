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

    public Product createProduct(String reference, String name, String description, BigDecimal price) {
        Product product = new Product();
        product.setReference(reference);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        return productRepository.save(product);
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(String.valueOf(id));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public boolean deleteProductById(int id) {
        if (productRepository.existsById(String.valueOf(id))) {
            productRepository.deleteById(String.valueOf(id));
            return true;
        }
        return false;
    }

    public boolean updateProduct(int id, Product updatedProduct) {
        Optional<Product> productOptional = productRepository.findById(String.valueOf(id));
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
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
