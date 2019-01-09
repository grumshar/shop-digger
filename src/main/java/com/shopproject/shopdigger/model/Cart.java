package com.shopproject.shopdigger.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.HashMap;

@Component
@SessionScope
public class Cart {

    private HashMap< CartItem, Double > userCart =
            new HashMap< CartItem,Double >();

    private BigDecimal total;

    public HashMap<CartItem, Double> getUserCart() {
        return userCart;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }


}
