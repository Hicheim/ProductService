package com.shopping.product.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Simple empty class for test purpose
 * //TODO  To be completed
 */
@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    private String country;

    private String postalCode;

    private String state;

    private String street;

}
