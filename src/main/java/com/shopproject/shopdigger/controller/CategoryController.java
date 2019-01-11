package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.converters.CategoryConverter;
import com.shopproject.shopdigger.dto.CategoryDto;
import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.model.Product;
import com.shopproject.shopdigger.service.CategoryService;
import com.shopproject.shopdigger.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CategoryController {

    private CategoryService categoryService;
    private CategoryConverter categoryConverter;
    private PaginationService paginationService;

    @Autowired
    public CategoryController(CategoryService categoryService, CategoryConverter categoryConverter, PaginationService paginationService) {
        this.categoryService = categoryService;
        this.categoryConverter = categoryConverter;
        this.paginationService = paginationService;
    }

    @GetMapping("/add-category")
    public String addCategory(Model model){
        model.addAttribute("mainCategories");
        model.addAttribute("categoryToAdd", new CategoryDto());
        return "add-category";
    }

    @PostMapping("/add-category")
    public String saveCategory(@Valid @ModelAttribute("categoryToAdd") CategoryDto categoryDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("mainCategories");
            return "add-category";
        }
        Category category = categoryConverter.convertToEntity(categoryDto);
        categoryService.save(category);
        return "redirect:/add-category";
    }

    @GetMapping("/edit-category/{id}")
    public String editCategory(@PathVariable Long id, Model model){
        Category category = categoryService.getCategoryById(id);
        CategoryDto categoryDto = categoryConverter.convertToDto(category);
        model.addAttribute("mainCategories");
        model.addAttribute("categoryToEdit", categoryDto);
        return "edit-category";
    }

    @PostMapping("edit-category")
    public String saveEditedCategory(@Valid @ModelAttribute("categoryToEdit") CategoryDto categoryDto, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("mainCategories");
            return "edit-category";
        }
        Category category = categoryConverter.convertToEntity(categoryDto);
        categoryService.save(category);
        return "redirect:/admin-category-list";
    }

    @GetMapping("/admin-category-list")
    public String showProductList(Model model, @RequestParam(value = "page", required = false) Integer page){
        if(page == null){
            page = 1;
        }
        int size = 8;

        List<CategoryDto> categoryList = paginationService.getAllCategoriesPaged(PageRequest.of(page - 1, size));

        int totalPages;
        long totalPagesCondition = categoryService.countAll() / size;
        if(categoryService.countAll() % size == 0 ){
            totalPages = (int)totalPagesCondition;
        } else {
            totalPages = (int)totalPagesCondition + 1;
        }

        List<Integer> pages = paginationService.countPaginationButtonsRange(totalPages, page);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", pages);
        model.addAttribute("categoryList", categoryList);
        return "admin-category-list";
    }

    @PostMapping("/admin-category-list")
    public String listBooks(@RequestParam("id") Long id){
        Category category = categoryService.getCategoryById(id);
        categoryService.delete(category);
        return "redirect:/admin-category-list";
    }
}
