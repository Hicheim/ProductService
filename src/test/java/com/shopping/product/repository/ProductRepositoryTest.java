package com.shopping.product.repository;

import com.shopping.product.model.Product;
import com.shopping.product.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository underTest ;
    //private static AutoCloseable autoCloseable;
    @AfterEach
    void tearDown() {
     //   underTest.deleteAll();
    }

    @DisplayName("JUnit test for CreateProduct_ProductDoesNotExist case")
    @Test
    void testCreateProduct_ProductDoesNotExists() {
        // given
        Product product = new Product(
                1," ", " "," ",new BigDecimal("19.99"));

        underTest.save(product);
        List<Product> Exist =
         underTest.findByReferenceAndName(product.getReference(), product.getName());
        //assertThat(Exist).isFalse();
    }

    @DisplayName("JUnit test for CreateProduct_ProductDoesNotExist case")
    @Test
    void testCreateProduct_ProductExists() {
        // given
        Product product = new Product(
                1," ", " "," ",new BigDecimal("19.99"));
        //underTest.save(product);
        //Boolean Exist = underTest.findByReferenceAndNameBoolean(product.getReference(), product.getName());
        //assertThat(Exist).isTrue();
    }

}