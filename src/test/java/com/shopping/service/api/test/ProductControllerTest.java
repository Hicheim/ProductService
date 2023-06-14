package com.shopping.service.api.test;

import com.shopping.product.api.ProductController;
import com.shopping.product.model.Product;
import com.shopping.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProductControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productRepository)).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product product1 = Product.builder()
                .id(1)
                .name("Product 1")
                .description("Description 1")
                .price(new BigDecimal("10.00"))
                .build();

        Product product2 = Product.builder()
                .id(2)
                .name("Product 2")
                .description("Description 2")
                .price(new BigDecimal("20.00"))
                .build();

        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(product1.getId()))
                .andExpect(jsonPath("$[0].name").value(product1.getName()))
                .andExpect(jsonPath("$[0].description").value(product1.getDescription()))
                .andExpect(jsonPath("$[0].price").value(product1.getPrice().doubleValue()))
                .andExpect(jsonPath("$[1].id").value(product2.getId()))
                .andExpect(jsonPath("$[1].name").value(product2.getName()))
                .andExpect(jsonPath("$[1].description").value(product2.getDescription()))
                .andExpect(jsonPath("$[1].price").value(product2.getPrice().doubleValue()));

        verify(productRepository, times(1)).findAll();
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void testGetProductById() throws Exception {
        int productId = 1;
        Product product = Product.builder()
                .id(productId)
                .name("Product 1")
                .description("Description 1")
                .price(new BigDecimal("10.00"))
                .build();

        when(productRepository.findById(String.valueOf(productId))).thenReturn(Optional.of(product));

        mockMvc.perform(get("/products/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andExpect(jsonPath("$.description").value(product.getDescription()))
                .andExpect(jsonPath("$.price").value(product.getPrice().doubleValue()));

        verify(productRepository, times(1)).findById(String.valueOf(productId));
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product = Product.builder()
                .id(1)
                .name("New Product")
                .description("New Description")
                .price(new BigDecimal("15.00"))
                .build();

        when(productRepository.save(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Product\",\"description\":\"New Description\",\"price\":15.00}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andExpect(jsonPath("$.description").value(product.getDescription()))
                .andExpect(jsonPath("$.price").value(product.getPrice().doubleValue()));

        verify(productRepository, times(1)).save(any(Product.class));
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        int productId = 1;
        Product product = Product.builder()
                .id(productId)
                .name("Product 1")
                .description("Description 1")
                .price(new BigDecimal("10.00"))
                .build();

        when(productRepository.existsById(String.valueOf(productId))).thenReturn(true);
        when(productRepository.save(any(Product.class))).thenReturn(product);

        mockMvc.perform(put("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Product\",\"description\":\"Updated Description\",\"price\":20.00}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(product.getId()))
                .andExpect(jsonPath("$.name").value(product.getName()))
                .andExpect(jsonPath("$.description").value(product.getDescription()))
                .andExpect(jsonPath("$.price").value(product.getPrice().doubleValue()));

        verify(productRepository, times(1)).existsById(String.valueOf(productId));
        verify(productRepository, times(1)).save(any(Product.class));
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        int productId = 1;

        when(productRepository.existsById(String.valueOf(productId))).thenReturn(true);

        mockMvc.perform(delete("/products/{id}", productId))
                .andExpect(status().isNoContent());

        verify(productRepository, times(1)).existsById(String.valueOf(productId));
        verify(productRepository, times(1)).deleteById(String.valueOf(productId));
        verifyNoMoreInteractions(productRepository);
    }

}
