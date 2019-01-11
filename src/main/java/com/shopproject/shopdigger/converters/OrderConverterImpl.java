package com.shopproject.shopdigger.converters;

import com.shopproject.shopdigger.dto.OrderDto;
import com.shopproject.shopdigger.dto.OrderItemDto;
import com.shopproject.shopdigger.dto.ProductDto;
import com.shopproject.shopdigger.model.Order;
import com.shopproject.shopdigger.model.OrderItem;
import com.shopproject.shopdigger.model.Product;
import com.shopproject.shopdigger.service.OrderItemService;
import com.shopproject.shopdigger.service.OrderService;
import com.shopproject.shopdigger.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverterImpl implements OrderConverter {

    private OrderService orderService;
    private ProductService productService;
    private ProductConverter productConverter;

    @Autowired
    public OrderConverterImpl(OrderService orderService, ProductService productService, ProductConverter productConverter) {
        this.orderService = orderService;
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @Override
    public OrderDto convertOrder(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setLocalDateTime(order.getOrderDate());
        List<OrderItem> orderItemList = order.getOrderItemList();
        List<OrderItemDto> orderItemDtoList = new ArrayList<>();

        for (OrderItem orderItem : orderItemList) {
            orderItemDtoList.add(convertOrderItem(orderItem));
        }
        orderDto.setOrderItemDtoList(orderItemDtoList);
        return orderDto;
    }

    @Override
    public OrderItemDto convertOrderItem(OrderItem orderItem) {

        Product product = productService.getProductById(orderItem.getId()).get();
        ProductDto productDto = productConverter.convertDto(product);
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPrice(productDto.getPrice());
        orderItemDto.setProductName(productDto.getName());
        return orderItemDto;
    }


    @Override
    public List<OrderDto> finalOrderConverter(Long id) {

        List<Order> orderList = orderService.findAllById(id);

        List<OrderDto> orderDtoList = new ArrayList<>();

        for (Order order : orderList) {
            orderDtoList.add(convertOrder(order));
        }

        return orderDtoList;

    }

}
