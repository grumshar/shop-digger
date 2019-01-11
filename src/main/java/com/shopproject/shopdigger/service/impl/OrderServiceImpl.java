package com.shopproject.shopdigger.service.impl;

import com.shopproject.shopdigger.dao.OrderRepository;
import com.shopproject.shopdigger.dao.UserRepository;
import com.shopproject.shopdigger.dto.UserDto;
import com.shopproject.shopdigger.model.*;
import com.shopproject.shopdigger.service.CartService;
import com.shopproject.shopdigger.service.OrderService;
import com.shopproject.shopdigger.service.ProductService;
import com.shopproject.shopdigger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.KeyStore;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private CartService cartService;
    private ProductService productService;
    private UserService userService;
    private UserRepository userRepository;



    @Autowired
    public OrderServiceImpl(CartService cartService,UserService userService,ProductService productService,OrderRepository orderRepository,UserRepository userRepository) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Order createOrder(Authentication authentication) {


        List<OrderItem> orderItemList = new ArrayList<>();
        HashMap<CartItem,Double> cartItemList = cartService.getCart().getUserCart();
        Iterator<CartItem> iterator = cartService.getCart().getUserCart().keySet().iterator();

        while (iterator.hasNext()) {

            Product product;
            
            for (Map.Entry<CartItem, Double> entry : cartItemList.entrySet()){
                CartItem key = entry.getKey();
                Double value = entry.getValue();

                product = productService.getProductById(key.getId()).get();
                orderItemList.add(new OrderItem(value,product));
                iterator.next();

            }



        }

        UserDto userDto = (UserDto) authentication.getPrincipal();
        User user = userService.findById(userDto.getId());

        Order order = new Order(orderItemList,user);
        for (OrderItem orderItem : orderItemList) {
            orderItem.setOrderId(order);
        }
        return order;
    }

    @Override
    @Transactional
    public void saveOrder(Authentication authentication){
        orderRepository.save(createOrder(authentication));
    }

    @Override
    public List<Order> findAllById(Long id) {

        User user = userRepository.findUserById(id);
        List<Order> orderList = orderRepository.findAllByUser(user);
        return orderList;
    }

    @Override
    public List<Order> findAllOrders(){


        Iterable<Order> iterable = orderRepository.findAll();
        List<Order> allOrdersList = new ArrayList<>();

        iterable.forEach(order -> allOrdersList.add(order));

       return allOrdersList;
    }
}
