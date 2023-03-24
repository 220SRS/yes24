package com.bookstore.yes24.order.dto.response;

import com.bookstore.yes24.order.Order;
import com.bookstore.yes24.order.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDto {

    private List<OrderBookResponseDto> orderBookResponseDtoList;

    private OrderStatus orderStatus;

    public static OrderResponseDto of(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();

        List<OrderBookResponseDto> bookList = OrderBookResponseDto.ofList(order.getOrderBooks());

        orderResponseDto.setOrderBookResponseDtoList(bookList);

        orderResponseDto.setOrderStatus(order.getOrderStatus());

        return orderResponseDto;
    }

    public static List<OrderResponseDto> ofList(List<Order> orderList) {

        List<OrderResponseDto> list = new ArrayList<>();

        for (Order order : orderList) {
            list.add(OrderResponseDto.of(order));
        }

        return list;
    }
}
