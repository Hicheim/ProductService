package com.shopping.product.repository;

import com.shopping.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // You can add custom query methods or use the default methods provided by JpaRepository
    List<Product> findByReferenceAndName(String reference, String name);
    List<Product> findByReference(String reference);
}
