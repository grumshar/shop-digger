package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.model.CartItem;
import com.shopproject.shopdigger.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class CartController {

    private CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/cart")
    public String cartView(Model model) {

        model.addAttribute("cartList",cartService.getCart().getUserCart());


        return "cart";
    }

    @PostMapping("/edit-cart")
    public String cartEdit(@RequestParam Long id,@RequestParam Double amount){

        cartService.editCartItem(id,amount);

        return "redirect:/cart";
    }

    @PostMapping("/delete-cart")
    public String cartItemDelete(@RequestParam Long id){

        cartService.delateCartProduct(id);

        return "redirect:/cart";
    }
}

