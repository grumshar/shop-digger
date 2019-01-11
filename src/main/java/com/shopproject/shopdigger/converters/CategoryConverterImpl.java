package com.shopproject.shopdigger.converters;

import com.shopproject.shopdigger.dto.CategoryDto;
import com.shopproject.shopdigger.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverterImpl implements CategoryConverter {

    @Override
    public Category convertToEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setProductList(categoryDto.getProductList());
        category.setParentCategoryId(categoryDto.getParentCategoryId());
        return category;
    }

    @Override
    public CategoryDto convertToDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setProductList(category.getProductList());
        categoryDto.setParentCategoryId(category.getParentCategoryId());
        return categoryDto;
    }
}
