package com.shopproject.shopdigger.converters;

import com.shopproject.shopdigger.dto.CategoryDto;
import com.shopproject.shopdigger.model.Category;

public interface CategoryConverter {

    Category convertToCategory(CategoryDto categoryDto);
    CategoryDto convertToDto(Category category);

}
