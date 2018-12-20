package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.model.Product;
import com.shopproject.shopdigger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/add-category")
    public String addCategory(Model model){
        categoryService.save(new Category("Alkohole", null, new ArrayList<Product>()));
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
