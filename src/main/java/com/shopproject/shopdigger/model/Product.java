package com.shopproject.shopdigger.model;

import com.shopproject.shopdigger.model.enums.Unit;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;
    @Enumerated(EnumType.STRING)
    private Unit unit;
    private double unitAmount;
    @Lob
    private String description;
    private Long eanCode;
    private boolean highlighted;

    public Product() {
    }

    public Product(String name, BigDecimal price, Category category, Unit unit, double unitAmount, String description, Long eanCode, boolean highlighted) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.unit = unit;
        this.unitAmount = unitAmount;
        this.description = description;
        this.eanCode = eanCode;
        this.highlighted = highlighted;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEanCode() {
        return eanCode;
    }

    public void setEanCode(Long eanCode) {
        this.eanCode = eanCode;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", unit=" + unit +
                ", unitAmount=" + unitAmount +
                ", description='" + description + '\'' +
                ", eanCode=" + eanCode +
                '}';
    }

}
