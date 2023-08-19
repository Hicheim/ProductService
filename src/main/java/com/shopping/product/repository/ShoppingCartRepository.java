package com.shopping.product.repository;

import com.shopping.product.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, UUID> {
    // You can add custom query methods or use the default methods provided by JpaRepository
}
