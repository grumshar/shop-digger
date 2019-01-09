package com.shopproject.shopdigger.service.impl;

import com.shopproject.shopdigger.converters.ProductConverterImpl;
import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Cart;
import com.shopproject.shopdigger.model.CartItem;
import com.shopproject.shopdigger.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

   private Cart cart;
   private ProductConverterImpl productConverter;

   @Autowired
   public CartServiceImpl(Cart cart, ProductConverterImpl productConverter) {
        this.cart = cart;
        this.productConverter = productConverter;
   }

    @Override
    public void addCartProduct(ProductDto productDto, Double amount) {
        if (cart.getUserCart().containsKey(productConverter.convertCartItem(productDto))){

           // words.computeIfPresent("hello", (k, v) -> v + 1);
           Double temp = cart.getUserCart().get(productConverter.convertCartItem(productDto)) + amount;
           cart.getUserCart().put(productConverter.convertCartItem(productDto),temp);
        } else {
            cart.getUserCart().put(productConverter.convertCartItem(productDto),amount);
        }

        totalPrice();

    }

    @Override
    public void delateCartProduct(Long id) {

        cart.getUserCart().entrySet().removeIf(cartitem -> cartitem.getKey().getId().equals(id));
        totalPrice();

    }

    @Override
    public void totalPrice() {
        BigDecimal temp = new BigDecimal(0);
        BigDecimal finlaresult = new BigDecimal(0);

        for(Map.Entry<CartItem, Double> entry : cart.getUserCart().entrySet()){

            finlaresult = temp.add(entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())));
            temp = finlaresult;


        }
        cart.setTotal(finlaresult);
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    public Map<CartItem,Double> getCartList(){
       return getCart().getUserCart();
    }

    @Override
    public void editCartItem(Long id,Double amount) {


        for(Map.Entry<CartItem, Double> entry : cart.getUserCart().entrySet()){

            if (entry.getKey().getId().equals(id)){
                CartItem cartItem =  entry.getKey();
                cart.getUserCart().put(cartItem, amount);
                totalPrice();
            } }
   }
}
