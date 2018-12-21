package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Product;
import com.shopproject.shopdigger.model.enums.Unit;
import com.shopproject.shopdigger.service.CategoryService;
import com.shopproject.shopdigger.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/product-page/{id}")
    public String showProductPage(Model model, @PathVariable Long id){
        Product p1 = new Product();
        p1.setName("Jabłko");
        p1.setPrice(new BigDecimal(10.59).setScale(2, RoundingMode.HALF_UP));
        productService.saveProduct(p1);
        Optional<Product> productToDisplay = productService.getProductById(id);
        productToDisplay.ifPresent(product ->
                model.addAttribute("productToDisplay", modelMapper.map(product, ProductDto.class)));
        productToDisplay.ifPresent(product ->
                model.addAttribute("classToDisplay", modelMapper.map(product, ProductDto.class).getClass()));
        return "product-page";
    }

    @GetMapping("/add-product")
    public String showAddProductPage(Model model, @RequestParam(value = "parentCategory", required = false) Long id, @ModelAttribute("productToAdd") Product product){
        model.addAttribute("productToAdd", new Product());
        //model.addAttribute("categories", categoryService.findCategoriesByParentCategoryId(null));
        model.addAttribute("subcategories", categoryService.getAllCategoriesList());
        model.addAttribute("units", Unit.values());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String showAddProductPage(@ModelAttribute("productToAdd") Product product){
        productService.saveProduct(product);
        return "add-product";
    }

    @GetMapping("/admin-product-list")
    public String listBooks(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);

        Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("productPage", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin-product-list";
    }

    @GetMapping("/admin-panel")
    public String showAdminPanel(){
        return "admin-panel";
    }

    @GetMapping("/delete-confirmation/{id}")
    public String showDeleteConfirmationPage(Model model, @PathVariable Long id){
        Product product = productService.getProductById(id).get();
        model.addAttribute("productToDelete", modelMapper.map(product, ProductDto.class));
        return "delete-confirmation";
    }

    @PostMapping("/delete")
    public String deleteConfirmationDecision(@RequestParam String decision, @RequestParam Long id){
        if(decision.equals("YES")){
            productService.deleteProduct(productService.getProductById(id).get());
        }
        return "redirect:/admin-product-list";
    }

    @GetMapping("/edit-product/{id}")
    public String showEditPage(@PathVariable Long id, Model model){
        Product product = productService.getProductById(id).get();
        model.addAttribute("productToEdit", product);
        model.addAttribute("units", Unit.values());
        model.addAttribute("subcategories", categoryService.getAllCategoriesList());
        return "edit-product";
    }
}
