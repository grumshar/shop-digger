package com.shopproject.shopdigger.model;

import javax.persistence.*;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;
    private double quantity;
    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product productId;

    public OrderItem() {
    }

    public OrderItem(double quantity, Product productId) {
        this.quantity = quantity;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrderId() {
        return order;
    }

    public void setOrderId(Order order) {
        this.order = order;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
    }



    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", order=" + order +
                ", quantity=" + quantity +
                ", productId=" + productId +
                '}';
    }
}
