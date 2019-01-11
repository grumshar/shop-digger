package com.shopproject.shopdigger.dao;

import com.shopproject.shopdigger.model.Order;
import com.shopproject.shopdigger.model.OrderItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

    List<OrderItem> findAllByOrder(Order order);

}
