package com.shopproject.shopdigger.model;

import com.shopproject.shopdigger.model.enums.OrderStatus;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "[order]")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItemList;
    @Enumerated
    private OrderStatus orderStatus;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Order() {
    }

    public Order(List<OrderItem> orderItemList,User user) {
        this.orderDate = LocalDateTime.now();
        this.orderItemList = orderItemList;
        this.orderStatus = OrderStatus.CREATED;
        this.user = user;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
