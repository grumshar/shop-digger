package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.model.Product;
import com.shopproject.shopdigger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/add-category")
    public String addCategory(Model model){
        model.addAttribute("categories", categoryService.findCategoriesByParentCategoryId(null));
        model.addAttribute("categoryToAdd", new Category());
        return "add-category";
    }

    @PostMapping("/add-category")
    public String saveCategory(@ModelAttribute Category category){
        categoryService.save(category);
        return "redirect:/add-category";
    }
}
