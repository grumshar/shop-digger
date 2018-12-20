package com.shopproject.shopdigger.service.impl;

import com.shopproject.shopdigger.dao.CategoryRepository;
import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findCategoriesByParentCategoryId(Long id) {
        return categoryRepository.findCategoriesByParentCategoryId(id);
    }

    @Override
    public boolean save(Category category) {
        categoryRepository.save(category);
        return true;
    }

    public List<Category> getAllCategoriesList(){
        Iterable<Category> unsortedList = categoryRepository.findAll();
        List<Category> sortedList = new ArrayList<>();
        return null;
    }
}
