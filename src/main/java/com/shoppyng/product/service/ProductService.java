package com.shoppyng.product.service;

import com.shoppyng.product.model.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {


    private final Map<String, Product> products = new HashMap<String, Product>();

    /**
     * For test purpose
     */
    @PostConstruct
    public void init() {
        Product prod1 = createProduct("Milka");
        Product prod2 = createProduct("Cheese");
        this.products.put(prod1.getReference(), prod1);
        this.products.put(prod2.getReference(), prod2);
    }

    public Product createProduct(String name, String ref, String desc, BigDecimal price) {
        Product prod = new Product( null, null,null,null );
        prod.setName(name);
        prod.setReference(ref);
        prod.setDescription(desc);
        prod.setPrice(price);
        this.products.put(prod.getReference(), prod);
        return prod;
    }
    public Product createProduct(String name) {
        Product prod = new Product( null, null,null,null );
        prod.setName(name);
        prod.setReference(null);
        prod.setDescription(null);
        prod.setPrice(BigDecimal.valueOf(0));
        this.products.put(prod.getReference(), prod);
        return prod;
    }

    public Optional<Product> getProduct(String productReference) {
        return Optional.ofNullable(products.get(productReference));
    }

    public boolean deleteProduct(String id) {
        if (getProduct(id).isEmpty()) return false;
        else {
            Optional<Product> prod = getProduct(id);
            products.remove(id);
            return true;
        }
    }

    public boolean updateProduct(String id, Product product) {
        if (getProduct(id).isEmpty()) return false;
        else {
            Optional<Product> prod = getProduct(id);
            products.replace(id,product);
            return true;
        }

    }
}
