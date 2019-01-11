package com.shopproject.shopdigger.service;

import com.shopproject.shopdigger.model.Order;
import com.shopproject.shopdigger.model.OrderItem;

import java.util.List;

public interface OrderItemService {

    public List<OrderItem> findAllByOrderId(Order order);
}
