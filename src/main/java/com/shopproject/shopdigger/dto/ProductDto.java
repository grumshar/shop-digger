package com.shopproject.shopdigger.dto;

import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.model.enums.Unit;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class ProductDto {

    private Long id;
    @NotNull
    @Size(min=1, message = "Enter product name, please.")
    private String name;
    @NotNull(message = "Enter product price, please.")
    @DecimalMin(value = "0.01", message = "Price should be over 0.01 PLN.")
    private BigDecimal price;
    @NotNull(message = "Choose product category, please.")
    private Category category;
    @NotNull(message = "Choose product unit, please.")
    private Unit unit;
    @NotNull(message = "Enter unit amount, please.")
    @DecimalMin(value = "0.1", message = "Unit amount should be over 1.")
    private double unitAmount;
    @NotNull
    @Size(min = 1, message = "Enter product description, please.")
    private String description;
    @NotNull(message = "Enter product EAN code, please.")
    @Min(value = 1000000000000L, message = "EAN code should be a 13 digit number.")
    @Max(value = 9999999999999L, message = "EAN code should be a 13 digit number.")
    private Long eanCode;
    private boolean highlighted;
    @NotNull
    @Size(min = 1, message = "Enter product image URL, please.")
    private String imgUrl;
    private String categoryName;

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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDto that = (ProductDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
