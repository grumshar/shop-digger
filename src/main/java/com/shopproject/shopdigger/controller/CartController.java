package com.shopproject.shopdigger.controller;

import com.shopproject.shopdigger.converters.AddressConverter;
import com.shopproject.shopdigger.dto.UserDto;
import com.shopproject.shopdigger.model.Address;
import com.shopproject.shopdigger.service.AddressService;
import com.shopproject.shopdigger.service.CartService;
import com.shopproject.shopdigger.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private AddressService addressService;
    private CartService cartService;
    private OrderService orderService;
    private AddressConverter addressConverter;


    @Autowired
    public CartController(CartService cartService, OrderService orderService,AddressService addressService,AddressConverter addressConverter) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.addressService = addressService;
        this.addressConverter = addressConverter;
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

    @PostMapping("/put-order")
    public String putOrder(Authentication authentication){

        orderService.saveOrder(authentication);


        return "order";
    }


    @PostMapping("/authenticated/order-confirmation")
    public String orderConfirmation(Model model,Authentication authentication) {

        UserDto userDto = (UserDto) authentication.getPrincipal();

        model.addAttribute("address",userDto.getAddressDto());

        model.addAttribute("cartList",cartService.getCart().getUserCart());


        return "authenticated/order-confirmation";
    }

    @PostMapping("/save-address-order")
    public String cartOrderConfirmAddress(@ModelAttribute Address address, Authentication authentication,Model model) {

        UserDto userDto =(UserDto) authentication.getPrincipal();
        addressService.saveAdress(address);
        Address address1 = addressService.findById(address.getId());
        userDto.setAddress(addressConverter.adressConverte(address1));
        model.addAttribute("cartList",cartService.getCart().getUserCart());


        return "authenticated/order-confirmation";
    }


}

