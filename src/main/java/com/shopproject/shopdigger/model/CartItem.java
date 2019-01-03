package com.shopproject.shopdigger.model;

public class CartItem {

    private Long id;
    private int productAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProductAmount() {
        return productAmount;
    }

    public void setProductAmount(int productAmount) {
        this.productAmount = productAmount;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", productAmount=" + productAmount +
                '}';
    }
}
