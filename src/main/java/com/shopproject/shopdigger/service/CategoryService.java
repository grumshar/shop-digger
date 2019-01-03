package com.shopproject.shopdigger.service;

import com.shopproject.shopdigger.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findCategoriesByParentCategoryId(Long id);
    boolean save(Category category);
    List<Category> getAllCategoriesList();
    List<Category> getCategoriesWhereParentCategoryNotNull();

}
