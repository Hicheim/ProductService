package com.shopping.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemRepository, String> {
    // You can add custom query methods or use the default methods provided by JpaRepository
}
