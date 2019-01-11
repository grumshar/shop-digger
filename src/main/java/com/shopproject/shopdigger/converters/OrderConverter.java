package com.shopproject.shopdigger.converters;

import com.shopproject.shopdigger.dto.OrderDto;
import com.shopproject.shopdigger.dto.OrderItemDto;
import com.shopproject.shopdigger.model.Order;
import com.shopproject.shopdigger.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;


public interface OrderConverter {

    public OrderDto convertOrder(Order order);

    public OrderItemDto convertOrderItem(OrderItem orderItem);

    public List<OrderDto> finalOrderConverter(Long id);
}
