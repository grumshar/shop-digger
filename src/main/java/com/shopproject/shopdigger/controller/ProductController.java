package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.converters.CategoryConverter;
import com.shopproject.shopdigger.converters.ProductConverter;
import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Product;
import com.shopproject.shopdigger.model.enums.Unit;
import com.shopproject.shopdigger.service.CategoryService;
import com.shopproject.shopdigger.service.PaginationService;
import com.shopproject.shopdigger.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;
    private ModelMapper modelMapper = new ModelMapper();
    private ProductConverter productConverter;
    private CategoryConverter categoryConverter;
    private PaginationService paginationService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService,
                             ProductConverter productConverter, CategoryConverter categoryConverter, PaginationService paginationService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.productConverter = productConverter;
        this.categoryConverter = categoryConverter;
        this.paginationService = paginationService;
    }

    @GetMapping("/add-product")
    public String showAddProductPage(Model model) {
        model.addAttribute("productToAdd", new ProductDto());
        model.addAttribute("subcategories");
        model.addAttribute("units", Unit.values());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String showAddProductPage(@Valid @ModelAttribute("productToAdd") ProductDto productDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("subcategories");
            model.addAttribute("units", Unit.values());
            return "add-product";
        }
        productService.saveProduct(productConverter.convert(productDto));
        return "redirect:/add-product";
    }

    @GetMapping("/edit-product/{id}")
    public String showEditPage(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).get();
        ProductDto productDto = productConverter.convertDto(product);
        model.addAttribute("productToEdit", productDto);
        model.addAttribute("units", Unit.values());
        model.addAttribute("subcategories");
        return "edit-product";
    }

    @PostMapping("/edit-product")
    public String saveEditedProduct(@Valid @ModelAttribute("productToEdit") ProductDto productDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("units", Unit.values());
            model.addAttribute("subcategories");
            return "edit-product";
        }
        Product product = productConverter.convert(productDto);
        productService.saveProduct(product);
        return "redirect:/admin-product-list";
    }

    @GetMapping("/admin-product-list")
    public String showProductList(Model model, @RequestParam(name = "page", required = false) Integer page) {
        if (page == null) {
            page = 1;
        }
        int size = 8;

        List<ProductDto> productList = paginationService.getAllProductsPaged(PageRequest.of(page - 1, size));

        int totalPages;
        long totalPagesCondition = productService.countAll() / size;
        if (productService.countAll() % size == 0) {
            totalPages = (int) totalPagesCondition;
        } else {
            totalPages = (int) totalPagesCondition + 1;
        }

        List<Integer> pages = paginationService.countPaginationButtonsRange(totalPages, page);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", pages);
        model.addAttribute("productList", productList);
        return "admin-product-list";
    }

    @PostMapping("/admin-product-list")
    public String listProducts(@RequestParam("id") Long id) {
        Optional<Product> product = productService.getProductById(id);
        if (productService.getProductById(id).isPresent()) {
            productService.deleteProduct(product.get());
        }
        return "redirect:/admin-product-list";
    }

    @GetMapping("/admin-panel")
    public String showAdminPanel() {
        return "admin-panel";
    }

    @GetMapping("/add-highlighted")
    public String listProductsContainingGet(Model model, @RequestParam(name = "page", required = false) Integer page,
                                            @RequestParam(name = "name", required = false) String name) {
        if (page == null) {
            page = 1;
        }
        int size = 8;

        if (name == null) {
            name = "";
        }

        List<ProductDto> productList = paginationService.getProductsByNamePaged(name, PageRequest.of(page - 1, size));

        int totalPages;
        long totalPagesCondition = productService.countAll() / size;
        if (productService.countAll() % size == 0) {
            totalPages = (int) totalPagesCondition;
        } else {
            totalPages = (int) totalPagesCondition + 1;
        }

        List<Integer> pages = paginationService.countPaginationButtonsRange(totalPages, page);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", pages);
        model.addAttribute("productList", productList);

        model.addAttribute("highlightedProducts", productService.getHighlightedProducts());

        return "add-highlighted";
    }

    @PostMapping("/add-highlighted")
    public String listProductsContainingPost(Model model, @RequestParam(name = "page", required = false) Integer page,
                                             @RequestParam(name = "name", required = false) String name, @RequestParam(required = false) Long id,
                                             @RequestParam(required = false) boolean decision) {
        if (page == null) {
            page = 1;
        }
        int size = 8;

        List<ProductDto> productList = paginationService.getProductsByNamePaged(name, PageRequest.of(page - 1, size));

        int totalPages;
        long totalPagesCondition = productService.countAll() / size;
        if (productService.countAll() % size == 0) {
            totalPages = (int) totalPagesCondition;
        } else {
            totalPages = (int) totalPagesCondition + 1;
        }

        if (null != id) {
            boolean flag = productService.getProductById(id).get().isHighlighted();
            productService.setHighlighted(id, decision);
            model.addAttribute("id", id);
            ProductDto productDto = productConverter.convertDto(productService.getProductById(id).get());
            model.addAttribute("modifiedProduct", productDto);
            String modified;
            if (decision) {
                if(flag){
                    modified = "already in";
                } else {
                    modified = "added to";
                }
            } else {
                modified = "removed from";
            }
            model.addAttribute("modifyVersion", modified);
        }

        List<Integer> pages = paginationService.countPaginationButtonsRange(totalPages, page);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("pages", pages);
        model.addAttribute("productList", productList);

        model.addAttribute("highlightedProducts", productService.getHighlightedProducts());

        return "add-highlighted";
    }

}
