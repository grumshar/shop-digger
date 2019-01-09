package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.converters.ProductConverter;
import com.shopproject.shopdigger.model.Product;
import com.shopproject.shopdigger.service.CartService;
import com.shopproject.shopdigger.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ShopController {

    private ProductService productService;
    private ProductConverter productConverter;
    private CartService cartService;

    @Autowired
    public ShopController(ProductService productService, ProductConverter productConverter, CartService cartService) {
        this.productService = productService;
        this.productConverter = productConverter;
        this.cartService = cartService;
    }

    @GetMapping("/test-page")
    public String test(Model model){
        model.addAttribute("products", productService.generateIndexProducts());
        return "home";
    }

    @PostMapping("/test-page")
    public String indexAddProduct(@RequestParam Long id, @RequestParam Double productAmount){
        cartService.addCartProduct(productConverter.convertDto(productService.getProductById(id).get()), productAmount);
        return "redirect:/test-page";
    }

    @GetMapping("/product-page/{id}")
    public String showProductPage(Model model, @PathVariable long id){
        Optional<Product> productToDisplay = productService.getProductById(id);
        productToDisplay.ifPresent(product ->
                model.addAttribute("productToDisplay", productConverter.convertDto(productToDisplay.get())));
        return "product-page";
    }

    @PostMapping("/product-page/{id}")
    public String addFromProductPageToCart(@RequestParam Long id, @RequestParam Double productAmount, Model model){
        cartService.addCartProduct(productConverter.convertDto(productService.getProductById(id).get()), productAmount);
        return "redirect:/product-page/" + id;
    }
}
