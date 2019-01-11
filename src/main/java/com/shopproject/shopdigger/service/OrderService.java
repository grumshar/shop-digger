package com.shopproject.shopdigger.service;

import com.shopproject.shopdigger.model.Order;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface OrderService {

    public Order createOrder(Authentication authentication);
    public void saveOrder(Authentication authentication);
    public List<Order> findAllById(Long id);
    public List<Order> findAllOrders();
}
