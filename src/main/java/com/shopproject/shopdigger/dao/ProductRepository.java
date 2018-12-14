package com.shopproject.shopdigger.dao;

import com.shopproject.shopdigger.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
