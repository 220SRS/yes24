package com.bookstore.yes24.order.dto;

import com.bookstore.yes24.order.OrderStatus;
import lombok.Getter;

@Getter
public class OrderUpdateDto {

    private Long orderId;

    private OrderStatus orderStatus;

}
