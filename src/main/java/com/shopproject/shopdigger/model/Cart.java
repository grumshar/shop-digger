package com.shopproject.shopdigger.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.HashMap;

@Component
@SessionScope
public class Cart {

    private HashMap< CartItem, Double > userCat =
            new HashMap< CartItem,Double >();


    private BigDecimal total;

    public HashMap<CartItem, Double> getUserCart() {
        return userCat;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
