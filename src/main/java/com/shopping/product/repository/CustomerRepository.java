package com.shopping.product.repository;

import com.shopping.product.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // You can add custom query methods or use the default methods provided by JpaRepository
}
