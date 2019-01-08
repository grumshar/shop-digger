package com.shopproject.shopdigger.service;

import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Cart;

public interface CartService {

    public void addCartProduct(ProductDto productDto,Double amount);
    public void delateCartProduct(Long id);
    public void totalPrice();
    Cart getCart();
}
