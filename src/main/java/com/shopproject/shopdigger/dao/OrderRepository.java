package com.shopproject.shopdigger.dao;

import com.shopproject.shopdigger.model.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
