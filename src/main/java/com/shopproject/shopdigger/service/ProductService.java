package com.shopproject.shopdigger.service;

import com.shopproject.shopdigger.model.Product;

import java.util.Optional;

public interface ProductService {

    Optional<Product> getProductById(Long productId);
    boolean saveProduct(Product product);

}
