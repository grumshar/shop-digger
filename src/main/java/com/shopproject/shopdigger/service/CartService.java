package com.shopproject.shopdigger.service;

import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Cart;
import com.shopproject.shopdigger.model.CartItem;

import java.util.Map;

public interface CartService {

    public void addCartProduct(ProductDto productDto,Double amount);
    public void delateCartProduct(Long id);
    public void totalPrice();
    Cart getCart();
    public Map<CartItem,Double> getCartList();
    public void editCartItem(Long id,Double amount);
}
