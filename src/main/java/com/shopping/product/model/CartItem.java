package com.shopping.product.model;

import java.math.BigDecimal;

/**
 * This is a simple implementation of a cart item
 * In real world, this could contains more information, like properties from the product (product attributes , parent category, quantity unit, discounts ...)
 */
public class CartItem {

    private String code;
    private String name;
    private String description;
    private BigDecimal unitPrice;
    private BigDecimal quantity;
    private BigDecimal calculatedPrice;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getCalculatedPrice() {
        return calculatedPrice;
    }

    public void setCalculatedPrice(BigDecimal calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

}