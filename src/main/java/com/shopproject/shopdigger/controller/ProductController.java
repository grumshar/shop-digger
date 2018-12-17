package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Category;
import com.shopproject.shopdigger.model.Product;
import com.shopproject.shopdigger.model.enums.Unit;
import com.shopproject.shopdigger.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Controller
public class ProductController {

    private ProductService productService;
    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
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
    public String showAddProductPage(Model model){
        model.addAttribute("productToAdd", new Product());
        model.addAttribute("unitType", Unit.values());
        return "add-product";
    }

    @PostMapping("/add-product")
    public String showAddProductPage(@ModelAttribute Product product){
        productService.saveProduct(product);
        return "add-product";
    }
}
