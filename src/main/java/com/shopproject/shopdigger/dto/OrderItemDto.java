package com.shopproject.shopdigger.dto;

import java.math.BigDecimal;

public class OrderItemDto {

    private double quantity;
    private BigDecimal price;
    private String productName;

    public OrderItemDto(){}

    public OrderItemDto(double quantity, BigDecimal price, String productName) {
        this.quantity = quantity;
        this.price = price;
        this.productName = productName;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
