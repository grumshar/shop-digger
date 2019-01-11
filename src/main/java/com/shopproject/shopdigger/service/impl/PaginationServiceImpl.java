package com.shopproject.shopdigger.service.impl;

import com.shopproject.shopdigger.converters.CategoryConverter;
import com.shopproject.shopdigger.converters.ProductConverter;
import com.shopproject.shopdigger.dao.CategoryRepository;
import com.shopproject.shopdigger.dao.ProductRepository;
import com.shopproject.shopdigger.dto.CategoryDto;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class PaginationServiceImpl implements PaginationService {

    private ProductRepository productRepository;
    private CategoryService categoryService;
    private ProductConverter productConverter;
    private CategoryRepository categoryRepository;
    private CategoryConverter categoryConverter;

    @Autowired
    public PaginationServiceImpl(ProductRepository productRepository, CategoryService categoryService, ProductConverter productConverter,
                                 CategoryRepository categoryRepository, CategoryConverter categoryConverter) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productConverter = productConverter;
        this.categoryRepository = categoryRepository;
        this.categoryConverter = categoryConverter;
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
    public List<ProductDto> getAllProductsPaged(PageRequest pageRequest){
        Iterable<Product> iterable =  productRepository.findAll(pageRequest);
        List<ProductDto> productDtoList = new ArrayList<>();
        iterable.forEach(product -> productDtoList.add(productConverter.convertDto(product)));
        return productDtoList;
    }

    @Override
    public List<CategoryDto> getAllCategoriesPaged(PageRequest pageRequest) {
        Iterable<Category> iterable = categoryRepository.findCategoriesByParentCategoryIdNotNull(pageRequest);
        List<CategoryDto> list = new ArrayList<>();
        iterable.forEach(category -> list.add(categoryConverter.convertToDto(category)));
        return list;
    }

    @Override
    public List<ProductDto> getProductsByNamePaged(String name, PageRequest pageRequest){
        List<Product> productList = productRepository.findProductsByNameContainingIgnoreCase(name, pageRequest);
        List<ProductDto> productDtoList = new ArrayList<>();
        productList.forEach(product -> productDtoList.add(productConverter.convertDto(product)));
        return productDtoList;
    }

    @Override
    public List<Integer> countPaginationButtonsRange(int totalPages, int page){
        int min;
        int max;
        if(totalPages < 5){
            min = 1;
            max = totalPages;
        } else {
            if(page - 2 <= 0){
                min = 1;
                max = 5;
            } else if(page + 2 >= totalPages){
                min = totalPages - 4;
                max = totalPages;
            }
            else {
                min = page - 2;
                max = page + 2;
            }
        }

        return IntStream.rangeClosed(min, max).boxed().collect(Collectors.toList());
    }

}
