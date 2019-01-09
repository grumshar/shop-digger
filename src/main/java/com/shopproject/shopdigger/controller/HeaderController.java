package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.service.CartService;
import com.shopproject.shopdigger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeaderController {

    private CartService cartService;
    private CategoryService categoryService;

    @Autowired
    public HeaderController(CartService cartService, CategoryService categoryService) {
        this.cartService = cartService;
        this.categoryService = categoryService;
    }

    @GetMapping("/header")
    public String showHeader(Model model){
        model.addAttribute("cartValue");
        model.addAttribute("bread");
        model.addAttribute("parentCategories");
        System.out.println(categoryService.findCategoriesByParentCategoryId(1L));
        return "header";
    }

}
