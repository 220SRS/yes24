package com.bookstore.yes24.order;

import com.bookstore.yes24.book.Book;
import com.bookstore.yes24.book.dto.BookResponseDto;
import com.bookstore.yes24.order.dto.*;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order orderUpdateDtoToOrder(OrderUpdateDto orderUpdateDto);

    List<OrderResponseDto> orderListToOrderResponseDtoList(List<Order> orderList);

    default OrderResponseDto orderToOrderResponseDto(Order order) {
        if (order == null) {
            return null;
        }
        else {
            OrderResponseDto orderResponseDto = new OrderResponseDto();

            List<OrderBookResponseDto> orderBookResponseDtoList = new ArrayList<>();

            orderResponseDto.setOrderId(order.getOrderId());

            orderResponseDto.setOrderStatus(order.getOrderStatus());

            order.getOrderBooks().stream()
                    .forEach(orderBook -> {
                        Book book = orderBook.getBook();
                        OrderBookResponseDto orderBookResponseDto =
                                new OrderBookResponseDto(
                                        book.getBookId(),
                                        book.getTitle(),
                                        book.getAuthor(),
                                        book.getPrice(),
                                        orderBook.getQuantity()
                                );
                        orderBookResponseDtoList.add(orderBookResponseDto);
                    });

            orderResponseDto.setOrderBookResponseDtoList(orderBookResponseDtoList);

            return orderResponseDto;
        }
    }
}
