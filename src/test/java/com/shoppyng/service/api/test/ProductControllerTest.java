package com.shoppyng.service.api.test;

import com.shoppyng.product.api.ProductController;
import com.shoppyng.product.model.Product;
import com.shoppyng.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;
    private Product mockProduct = new Product("1", "Sample Product", "createProduct", BigDecimal.valueOf(123412345678901L));

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProduct_Success() {
        // Mock the productService.getProduct() method
            when(productService.getProduct("1")).thenReturn(Optional.of(mockProduct));

        // Call the getProduct() method in the controller
        ResponseEntity<Product> response = productController.getProduct("1");

        // Verify the productService.getProduct() method is called once with the correct argument
        verify(productService, times(1)).getProduct("1");

        // Assert the response status code and the retrieved product
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockProduct, response.getBody());
    }

    @Test
    void testGetProduct_NotFound() {
        // Mock the productService.getProduct() method
        when(productService.getProduct(any())).thenReturn(Optional.empty());

        // Call the getProduct() method in the controller
        ResponseEntity<Product> response = productController.getProduct("1");

        // Verify the productService.getProduct() method is called once with the correct argument
        verify(productService, times(1)).getProduct("1");

        // Assert the response status code
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCreateProduct_Success() {
        Product mockProductUpdated = new Product("1", "Updated Product", "null", BigDecimal.valueOf(0));
        // Mock the productService.createProduct() method
        when(productService.createProduct("1", "Updated Product", "null", BigDecimal.valueOf(0))).thenReturn(mockProductUpdated);

        // Call the createProduct() method in the controller
        ResponseEntity<Product> response = productController.createProduct(mockProductUpdated);

        // Verify the productService.createProduct() method is called once with the correct argument
        verify(productService, times(1)).createProduct("1", "Updated Product", "null", BigDecimal.valueOf(0));

        // Assert the response status code and the created product
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockProductUpdated, response.getBody());
    }

    @Test
    void testUpdateProduct_Success() {
        // Mock the productService.updateProduct() method
        when(productService.updateProduct(eq("1"), any())).thenReturn(true);

        // Call the updateProduct() method in the controller
        Product updatedProduct = new Product("1", "Updated Product", "null", BigDecimal.valueOf(0));;
        ResponseEntity<Product> response = productController.updateProduct("1", updatedProduct);

        // Verify the productService.updateProduct() method is called once with the correct arguments
        verify(productService, times(1)).updateProduct(eq("1"), any());

        // Assert the response status code and the updated product
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProduct, response.getBody());
    }

    @Test
    void testUpdateProduct_NotFound() {
        // Mock the productService.updateProduct() method
        when(productService.updateProduct(eq("1"), any())).thenReturn(false);

        // Call the updateProduct() method in the controller
        Product updatedProduct = new Product("1", "Updated Product", "null", BigDecimal.valueOf(0));
        //(String s, String sample_product, String createProduct, BigDecimal i)
        ResponseEntity<Product> response = productController.updateProduct("1", updatedProduct);

        // Verify the productService.updateProduct() method is called once with the correct arguments
        verify(productService, times(1)).updateProduct(eq("1"), any());

        // Assert the response status code
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteProduct_Success() {
        // Mock the productService.deleteProduct() method
        when(productService.deleteProduct("1")).thenReturn(true);

        // Call the deleteProduct() method in the controller
        ResponseEntity<Void> response = productController.deleteProduct("1");

        // Verify the productService.deleteProduct() method is called once with the correct argument
        verify(productService, times(1)).deleteProduct("1");

        // Assert the response status code
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteProduct_NotFound() {
        // Mock the productService.deleteProduct() method
        when(productService.deleteProduct("1")).thenReturn(false);

        // Call the deleteProduct() method in the controller
        ResponseEntity<Void> response = productController.deleteProduct("1");

        // Verify the productService.deleteProduct() method is called once with the correct argument
        verify(productService, times(1)).deleteProduct("1");

        // Assert the response status code
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
