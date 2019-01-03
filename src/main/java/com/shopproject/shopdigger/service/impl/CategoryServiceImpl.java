package com.shopproject.shopdigger.service.impl;

import com.shopproject.shopdigger.dao.CategoryRepository;
import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Category> getAllCategories(){
        Iterable<Category> iterable = categoryRepository.findAll();
        List<Category> result = new ArrayList<>();
        iterable.forEach(category -> result.add(category));
        return result;
    }

    @Override
    public List<Category> getCategoriesWhereParentCategoryNotNull(){
        Iterable<Category> iterable = categoryRepository.findCategoriesByParentCategoryIdNotNull();
        List<Category> result = new ArrayList<>();
        iterable.forEach(category -> result.add(category));
        return result;
    }

    @Override
    public List<Category> getAllCategoriesList() {
        List<Category> categoryList = new ArrayList<>();
        categoryRepository.findAll().forEach(category -> categoryList.add(category));
        categoryList.sort(Comparator.comparing(Category::getName));
        List<Category> mainCategories = new ArrayList<>();
        List<Category> subCategories = new ArrayList<>();
        for(int i = 0; i < categoryList.size(); i++){
            if(categoryList.get(i).getParentCategoryId() == null){
                mainCategories.add(categoryList.get(i));
            } else {
                subCategories.add(categoryList.get(i));
            }
        }
        categoryList.clear();
        for(int i = 0; i < mainCategories.size(); i++){
            categoryList.add(mainCategories.get(i));
            for(int j = 0; j < subCategories.size(); j++){
                if(subCategories.get(j).getParentCategoryId().equals(mainCategories.get(i).getId())){
                    categoryList.add(subCategories.get(j));
                }
            }
        }
        return categoryList;
    }
}
