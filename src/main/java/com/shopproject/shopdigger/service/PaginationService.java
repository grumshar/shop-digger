package com.shopproject.shopdigger.service;

import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaginationService {

    Page<Product> getProductsByNameContainingIgnoreCase(Pageable pageable, String text);
    Page<Product> getHighlightedProductsPaged(Pageable pageable);
    Page<Category> getAllCategoriesPaged(Pageable pageable);

}
