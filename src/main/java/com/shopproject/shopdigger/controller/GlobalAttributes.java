package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.converters.CategoryConverter;
import com.shopproject.shopdigger.dto.CategoryDto;
import com.shopproject.shopdigger.service.CartService;
import com.shopproject.shopdigger.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalAttributes {

    private CartService cartService;
    private CategoryConverter categoryConverter;
    private CategoryService categoryService;

    @Autowired
    public GlobalAttributes(CartService cartService, CategoryConverter categoryConverter, CategoryService categoryService) {
        this.cartService = cartService;
        this.categoryConverter = categoryConverter;
        this.categoryService = categoryService;
    }

    @ModelAttribute("cartValue")
    public BigDecimal getCartValue() {
        if(cartService.getCart().getTotal() != null){
            return cartService.getCart().getTotal().setScale(2, RoundingMode.DOWN);
        } else {
            return new BigDecimal(0.00).setScale(2, RoundingMode.DOWN);
        }
    }

    @ModelAttribute("bread")
    public List<CategoryDto> getBreadSubcategories(){
        List<CategoryDto> finalList = new ArrayList<>();
        categoryService.findCategoriesByParentCategoryId(1L)
                .forEach(category -> finalList.add(categoryConverter.convertToDto(category)));
        return finalList;
    }

    @ModelAttribute("parentCategories")
    public List<CategoryDto> getParentCategories(){
        List<CategoryDto> finalList = new ArrayList<>();
        categoryService.findCategoriesByParentCategoryId(null)
                .forEach(category -> finalList.add(categoryConverter.convertToDto(category)));
        return finalList;
    }
}
