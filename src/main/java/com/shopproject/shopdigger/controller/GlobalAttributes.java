package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.math.BigDecimal;
import java.math.RoundingMode;

@ControllerAdvice
public class GlobalAttributes {

    private CartService cartService;

    @Autowired
    public GlobalAttributes(CartService cartService) {
        this.cartService = cartService;
    }

    @ModelAttribute("cartValue")
    public BigDecimal getCartValue() {
        if(cartService.getCart().getTotal() != null){
            return cartService.getCart().getTotal().setScale(2, RoundingMode.DOWN);
        } else {
            return new BigDecimal(0.00);
        }

    }
}
