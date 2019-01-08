package com.shopproject.shopdigger.model;

import com.shopproject.shopdigger.model.enums.Unit;

import java.math.BigDecimal;

public class CartItem {

    private Long id;
    private String name;
    private Unit unit;
    private double unitAmount;
    private BigDecimal price;

    public CartItem() {
    }

    public CartItem(Long id, String name, Unit unit, double unitAmount, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.unitAmount = unitAmount;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public double getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(double unitAmount) {
        this.unitAmount = unitAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
