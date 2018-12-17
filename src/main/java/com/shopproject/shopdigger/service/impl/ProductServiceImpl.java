package com.shopproject.shopdigger.service.impl;

import com.shopproject.shopdigger.dao.ProductRepository;
import com.shopproject.shopdigger.model.Product;
import com.shopproject.shopdigger.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProductById(Long productId){
        return productRepository.findById(productId);
    }

    @Override
    public boolean saveProduct(Product product) {
        productRepository.save(product);
        return true;
    }
}
