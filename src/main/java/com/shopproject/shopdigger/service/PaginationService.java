package com.shopproject.shopdigger.service;

import com.shopproject.shopdigger.dto.CategoryDto;
import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaginationService {

    Page<ProductDto> getProductsByNameContainingIgnoreCase(Pageable pageable, String text);
    List<ProductDto> getAllProductsPaged(PageRequest pageRequest);
    List<CategoryDto> getAllCategoriesPaged(PageRequest pageRequest);
    List<Integer> countPaginationButtonsRange(int totalPages, int page);
    List<ProductDto> getProductsByNamePaged(String name, PageRequest pageRequest);

}
