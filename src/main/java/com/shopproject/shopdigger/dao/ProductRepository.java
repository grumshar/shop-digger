package com.shopproject.shopdigger.dao;

import com.shopproject.shopdigger.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    List<Product> findProductsByNameContainingIgnoreCase(String text);
    List<Product> findProductsByHighlightedTrue();

}
