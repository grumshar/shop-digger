package com.shopproject.shopdigger.service.impl;

import com.shopproject.shopdigger.dao.CategoryRepository;
import com.shopproject.shopdigger.dao.ProductRepository;
import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.model.Product;
import com.shopproject.shopdigger.service.CategoryService;
import com.shopproject.shopdigger.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PaginationServiceImpl implements PaginationService {

    private ProductRepository productRepository;
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    @Autowired
    public PaginationServiceImpl(ProductRepository productRepository, CategoryService categoryService, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Product> getProductsByNameContainingIgnoreCase(Pageable pageable, String text) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> list;
        List<Product> products = productRepository.findProductsByNameContainingIgnoreCase(text);

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), products.size());
    }

    @Override
    public Page<Product> getHighlightedProductsPaged(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Product> list;
        List<Product> products = productRepository.findProductsByHighlightedTrue();

        if (products.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, products.size());
            list = products.subList(startItem, toIndex);
        }

        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), products.size());
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
