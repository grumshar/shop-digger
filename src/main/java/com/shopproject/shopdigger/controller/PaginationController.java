package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.model.Product;
import com.shopproject.shopdigger.service.PaginationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PaginationController {

    private PaginationService paginationService;

    @Autowired
    public PaginationController(PaginationService paginationService) {
        this.paginationService = paginationService;
    }

    @GetMapping("/add-highlighted")
    public String listProductsContainingGet(){
        return "add-highlighted";
    }

    @PostMapping("/add-highlighted")
    public String listProductsContainingPost(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size, @RequestParam(required = false) String name) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);

        Page<Product> productPage = paginationService.getProductsByNameContainingIgnoreCase(PageRequest.of(currentPage - 1, pageSize), name);

        model.addAttribute("productPage", productPage);
        model.addAttribute("name", name);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "add-highlighted";
    }

    @GetMapping("/admin-category-list")
    public String showAdminCategoryList(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);

        Page<Category> categoryPage = paginationService.getAllCategoriesPaged(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("categoryPage", categoryPage);
        model.addAttribute("name");

        int totalPages = categoryPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin-category-list";
    }
}
