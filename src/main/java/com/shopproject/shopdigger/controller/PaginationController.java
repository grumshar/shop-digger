package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.converters.ProductConverter;
import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.service.PaginationService;
import com.shopproject.shopdigger.service.ProductService;
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
    private ProductService productService;
    private ProductConverter productConverter;

    @Autowired
    public PaginationController(PaginationService paginationService, ProductService productService, ProductConverter productConverter) {
        this.paginationService = paginationService;
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @GetMapping("/add-highlighted")
    public String listProductsContainingGet(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam(required = false) String name){
        if(name != null){
            final int currentPage = page.orElse(1);
            final int pageSize = 10;

            Page<ProductDto> productPage = paginationService.getProductsByNameContainingIgnoreCase(PageRequest.of(currentPage - 1, pageSize), name);

            model.addAttribute("productPage", productPage);
            model.addAttribute("name", name);
            page.ifPresent(pageNo -> model.addAttribute("page", pageNo));

            int totalPages = productPage.getTotalPages();
            if (totalPages > 0) {
                List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
                model.addAttribute("pageNumbers", pageNumbers);
            }
        }

        model.addAttribute("highlightedProducts", productService.getHighlightedProducts());

        return "add-highlighted";
    }

    @PostMapping("/add-highlighted")
    public String listProductsContainingPost(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam(required = false) String name, @RequestParam(required = false) Long id, @RequestParam(required = false) boolean decision) {
        final int currentPage = page.orElse(1);
        final int pageSize = 10;

        Page<ProductDto> productPage = paginationService.getProductsByNameContainingIgnoreCase(PageRequest.of(currentPage - 1, pageSize), name);
        model.addAttribute("productPage", productPage);
        model.addAttribute("name", name);
        page.ifPresent(pageNo -> model.addAttribute("page", pageNo));

        if(null != id){
            productService.setHighlighted(id, decision);
            model.addAttribute("id", id);
            model.addAttribute("modifiedProduct",
                    productConverter.convertDto(productService.getProductById(id).get()));
            String modified;
            if(decision){
                modified = "added to";
            } else{
                modified = "removed from";
            }
            model.addAttribute("modifyVersion", modified);
        }

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("highlightedProducts", productService.getHighlightedProducts());

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
