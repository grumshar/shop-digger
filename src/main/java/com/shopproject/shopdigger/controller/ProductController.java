package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.converters.ProductConverter;
import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Cart;
import com.shopproject.shopdigger.model.Product;
import com.shopproject.shopdigger.model.enums.Unit;
import com.shopproject.shopdigger.service.CartService;
import com.shopproject.shopdigger.service.CategoryService;
import com.shopproject.shopdigger.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;
    private ModelMapper modelMapper = new ModelMapper();
    private CartService cartService;
    private ProductConverter productConverter;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, CartService cartService,
                             ProductConverter productConverter, Cart cart) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.cartService = cartService;
        this.productConverter = productConverter;
    }

    @GetMapping("/add-product")
    public String showAddProductPage(Model model){
        model.addAttribute("productToAdd", new ProductDto());
        model.addAttribute("subcategories", categoryService.getAllCategoriesList());
        model.addAttribute("units", Unit.values());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String showAddProductPage(@ModelAttribute("productToAdd") ProductDto productDto){
        productService.saveProduct(productConverter.convert(productDto));
        return "redirect:/add-product";
    }

    @GetMapping("/admin-product-list")
    public String listProducts(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        final int currentPage = page.orElse(1);
        final int pageSize = size.orElse(5);

        Page<Product> productPage = productService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("productPage", productPage);

        int totalPages = productPage.getTotalPages();
        List<Integer> pageNumbers;
        if (totalPages > 0 && page.isPresent()) {
            if(totalPages <= 5){
                pageNumbers = IntStream.rangeClosed(1, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
            }
            else if(page.get() <= 2){
                pageNumbers = IntStream.rangeClosed(1, 5)
                        .boxed()
                        .collect(Collectors.toList());
            } else if((totalPages - page.get()) <= 2){
                pageNumbers = IntStream.rangeClosed(page.get() - 5, totalPages)
                        .boxed()
                        .collect(Collectors.toList());
            } else {
                pageNumbers = IntStream.rangeClosed(page.get() - 2, page.get() + 2)
                        .boxed()
                        .collect(Collectors.toList());
            }
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "admin-product-list";
    }

    @PostMapping("/admin-product-list")
    public String listBooks(@RequestParam("id") Long id){
        Optional<Product> product = productService.getProductById(id);
        if(productService.getProductById(id).isPresent()){
            productService.deleteProduct(product.get());
        }
        return "redirect:/admin-product-list";
    }

    @GetMapping("/admin-panel")
    public String showAdminPanel(){
        return "admin-panel";
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

    @PostMapping("/edit-product")
    public String saveEditedProduct(@ModelAttribute ProductDto productDto){
        Product product = modelMapper.map(productDto, Product.class);
        productService.saveProduct(product);
        return "redirect:/admin-product-list";
    }

}
