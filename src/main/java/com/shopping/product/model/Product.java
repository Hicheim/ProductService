package com.shopping.product.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Builder
@Entity(name = "product")
public class Product {

    public Product() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String reference;
    @Column
    private String name;
    @Column
    private String description;

    @Column
    private BigDecimal price;

    public Product(long id, String reference, String name, String description, BigDecimal price) {
        this.id = id;
        this.reference = reference;
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
