package com.shopping.product.repository;

import com.shopping.product.model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @Autowired
     ProductRepository underTest ;

    @DisplayName("JUnit test for CreateProduct_ProductDoesNotExist case")
    @Test
    void testCreateProduct_ProductDoesNotExists() {
        // given
        Product product = new Product(
               1L, "", " "," ",new BigDecimal("19.99"));
        System.out.println(underTest.findByReferenceAndName(product.getReference(), product.getName()));
        //underTest.save(product);
                assertThat( underTest.findByReferenceAndName(product.getReference(), product.getName())).isEmpty();
    }

    @DisplayName("JUnit test for CreateProduct_ProductDoesNotExist case")
    @Test
    void testCreateProduct_ProductExists() {
        // given
        Product product = new Product(
              1L, "1", " "," ",new BigDecimal("19.99"));
        underTest.save(product);
        assertThat(underTest.findByReferenceAndName(product.getReference(), product.getName())).isNotEmpty();
    }

}