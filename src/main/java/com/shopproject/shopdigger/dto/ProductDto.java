package com.shopproject.shopdigger.dto;

import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.model.enums.Unit;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
public class ProductDto {

    private Long id;
    private String name;
    private BigDecimal price;
    private Category category;
    private Unit unit;
    private double unitAmount;
    private String description;
    private Long eanCode;

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

    @Override
    public String toString() {
        return "ProductDto{" +
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
