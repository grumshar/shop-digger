package com.shopproject.shopdigger.dao;

import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {

    List<Product> findProductsByNameContainingIgnoreCase(String text);
    List<Product> findProductsByHighlightedTrue();
    List<Product> findProductsByCategoryId(Long id);
    List<Product> findProductsByNameContainingIgnoreCase(String name, Pageable pageable);

}
