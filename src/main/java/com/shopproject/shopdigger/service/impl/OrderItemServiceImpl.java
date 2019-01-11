package com.shopproject.shopdigger.service.impl;

import com.shopproject.shopdigger.dao.OrderItemRepository;
import com.shopproject.shopdigger.model.Order;
import com.shopproject.shopdigger.model.OrderItem;
import com.shopproject.shopdigger.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {


    private OrderItemRepository orderItemRepository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public List<OrderItem> findAllByOrderId(Order order) {

        List<OrderItem> orderItemList = orderItemRepository.findAllByOrder(order);

        return orderItemList;
    }
}
