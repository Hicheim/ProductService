package com.shopping.product.service;

import com.shopping.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
//@WebMvcTest(ProductService.class)
//@ComponentScan("com.shopping.product.api")
//@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {


    @Mock
    private ProductRepository productRepository ;
    //private static AutoCloseable autoCloseable;
    @InjectMocks
    private ProductService productService ;

    @DisplayName("JUnit test for CreateProduct_ProductDoesNotExist case")
    @Test
    void testCreateProduct_ProductDoesNotExist() {
        // Arrange
        String reference = "REF123";
        String name = "Test Product";
        String description = "This is a test product.";
        BigDecimal price = new BigDecimal("19.99");
        Product createdProduct =  new Product(1,reference,name,description,price);
        // Mock the behavior of the productRepository.findByReferenceAndName method
        when(productRepository.findByReferenceAndName(reference, name)).thenReturn(null);
        when(productRepository.save(any())).thenReturn(createdProduct);

        // Act
        createdProduct = productService.createProduct(reference, name, description, price);

        // Assert
        assertNotNull(createdProduct);
        assertEquals(reference, createdProduct.getReference());
        assertEquals(name, createdProduct.getName());
        assertEquals(description, createdProduct.getDescription());
        assertEquals(price, createdProduct.getPrice());

        // Verify
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @DisplayName("JUnit test for CreateProduct_ProductExists case")
    @Test
    void testCreateProduct_ProductExists() {
        // Arrange
        String reference = "REF456";
        String name = "Existing Product";
        String description = "This is an existing product.";
        BigDecimal price = new BigDecimal("29.99");
        final Product product1 = new Product(1,reference, name, description, price);
        List<Product> prct=  new ArrayList<>();
        prct.add(product1);
        // Mock
        when(productRepository.findByReferenceAndName(reference, name)).thenReturn(prct);

        // Act
        Product createdProduct = productService.createProduct(reference, name, description, price);

        // Assert
        assertNull(createdProduct); // Since the product already exists, the method should return null

        // Verify
        verify(productRepository, never()).save(any(Product.class));
    }
    @DisplayName("JUnit test for GetAllProducts method")
    @Test
    void testGetAllProducts() {
        // Arrange
        List<Product> productList = List.of(new Product(), new Product());
        when(productRepository.findAll()).thenReturn(productList);

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        assertEquals(productList.size(), result.size());
    }

    @Test
    void getProductById() {
        long productId = 1L;
        Product productToFind = new Product();
        productToFind.setId(productId);
        when(productRepository.findById(productId)).thenReturn(Optional.of(productToFind));
        assertEquals(productService.getProductById(productId), Optional.of(productToFind));
        verify(productRepository).findById(productId);

    }

    @Test
    void getAllProducts() {
        productService.getAllProducts();
        verify(productRepository).findAll();
    }

    @Test
    void testDeleteProduct_ProductExists() {
        long productId = 1L;
        Product productToDelete = new Product();
        productToDelete.setId(productId);

        // Mock
        when(productRepository.findById(productId)).thenReturn(Optional.of(productToDelete));

         // Act
        boolean isDeleted = productService.deleteProductById(productId);

        // Assert
        assertTrue(isDeleted);
        verify(productRepository, times(1)).delete(productToDelete);
    }
    @Test
    void testDeleteProduct_ProductDoesNotExist() {
        // Arrange
        long productId = 1L;


        // Act
        boolean isDeleted = productService.deleteProductById(productId);

        // Assert
        assertFalse(isDeleted);
        verify(productRepository, never()).delete(any(Product.class));
    }

    @Test
    void updateProduct_whenFound() {
        long productId = 1L;
        String reference = "REF456";
        String name = "new Product";
        String description = "new description.";
        BigDecimal price = new BigDecimal("29.99");
        Product productToUpdate = new Product();
        productToUpdate.setId(productId);

        // Mock the behavior of productRepository.findById method
        when(productRepository.findById(productId)).thenReturn(Optional.of(productToUpdate));
        productToUpdate.setReference(reference);
        productToUpdate.setName(name);
        productToUpdate.setDescription(description);
        productToUpdate.setPrice(price);
        //when(productRepository.deleteById(productId));
        // Act
        boolean isChanged = productService.updateProduct(productId,productToUpdate);

        // Assert
        assertTrue(isChanged);
        verify(productRepository, times(1)).save(productToUpdate);
    }
    @Test
    void updateProduct_whenNotFound() {
        long productId = 1L;
        Product productToUpdate = new Product();
        productToUpdate.setId(productId);

        // Mock the behavior of productRepository.findById method
        when(productRepository.findById(productId)).thenReturn(null);
        // Assert
        //assertNull(productToUpdate);
        assertFalse(productService.updateProduct(productId,productToUpdate));
    }
}