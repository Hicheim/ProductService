package com.shopping.product.repository;

import com.shopping.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    // You can add custom query methods or use the default methods provided by JpaRepository
}
