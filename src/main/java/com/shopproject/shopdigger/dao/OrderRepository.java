package com.shopproject.shopdigger.dao;

import com.shopproject.shopdigger.model.Order;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findAllByUser(Long id);
}
