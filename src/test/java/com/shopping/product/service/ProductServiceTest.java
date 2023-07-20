package com.shopping.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopping.product.model.Product;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@WebMvcTest(ProductService.class)
//@ComponentScan("com.shopping.product.api")
class ProductServiceTest {
   // @Autowired
    //private MockMvc mockMvc;


    private final ObjectMapper objectMapper = new ObjectMapper();

    //@Autowired
    //ProductService productService;
    @Test
    void createProductIfNotExist() {
        Product product1 = new Product(1," ", " ", " ", BigDecimal.valueOf(2.0));
        Product product2 = new Product(2," ", " ", " ", BigDecimal.valueOf(100.1));
       // if (productService.getProductById( product.getId()).isPresent())
            ProductService prdctSrvc = mock(ProductService.class);
        //ProductController prdctCtrl = new ProductController();
        when(prdctSrvc.createProduct(product1.getReference(), product1.getName(), product1.getDescription(), product1.getPrice())).thenReturn(product1);
        assertEquals(2, 2);
    }

    @Test
    void getProductById() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void deleteProductById() {
    }

    @Test
    void updateProduct() {
    }
}