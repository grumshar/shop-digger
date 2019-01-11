package com.shopproject.shopdigger.dto;

import com.shopproject.shopdigger.model.OrderItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class OrderDto {

    private Long id;
    private LocalDateTime localDateTime;
    private List<OrderItemDto> orderItemDtoList;

    public OrderDto (){}


    public OrderDto(Long id, LocalDateTime localDateTime, List<OrderItem> orderItemList) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.orderItemDtoList = orderItemDtoList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }



    public List<OrderItemDto> getOrderItemDtoList() {
        return orderItemDtoList;
    }

    public void setOrderItemDtoList(List<OrderItemDto> orderItemDtoList) {
        this.orderItemDtoList = orderItemDtoList;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", localDateTime=" + localDateTime +
                ", orderItemDtoList=" + orderItemDtoList +
                '}';
    }
}


