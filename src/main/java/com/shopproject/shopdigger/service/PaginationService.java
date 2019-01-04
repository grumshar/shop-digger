package com.shopproject.shopdigger.service;

import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaginationService {

    Page<ProductDto> getProductsByNameContainingIgnoreCase(Pageable pageable, String text);
    Page<Category> getAllCategoriesPaged(Pageable pageable);

}
