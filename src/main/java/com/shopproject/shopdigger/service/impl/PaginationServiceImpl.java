package com.shopproject.shopdigger.service.impl;

import com.shopproject.shopdigger.converters.ProductConverter;
import com.shopproject.shopdigger.dao.CategoryRepository;
import com.shopproject.shopdigger.dao.ProductRepository;
import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.model.Product;
import com.shopproject.shopdigger.service.CategoryService;
import com.shopproject.shopdigger.service.PaginationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PaginationServiceImpl implements PaginationService {

    private ProductRepository productRepository;
    private CategoryService categoryService;
    private ProductConverter productConverter;

    @Autowired
    public PaginationServiceImpl(ProductRepository productRepository, CategoryService categoryService, ProductConverter productConverter) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productConverter = productConverter;
    }

    @Override
    public Page<ProductDto> getProductsByNameContainingIgnoreCase(Pageable pageable, String text) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ProductDto> list;
        List<Product> products = productRepository.findProductsByNameContainingIgnoreCase(text);
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach(product -> productDtos.add(productConverter.convertDto(product)));

        if (productDtos.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, productDtos.size());
            list = productDtos.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), productDtos.size());
    }

    @Override
    public Page<Category> getAllCategoriesPaged(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Category> list;
        List<Category> categories = categoryService.getCategoriesWhereParentCategoryNotNull();

        if (categories.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, categories.size());
            list = categories.subList(startItem, toIndex);
        }

        Page<Category> productPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), categories.size());

        return productPage;
    }
}
