package com.bookstore.yes24.order;

import com.bookstore.yes24.book.Book;
import com.bookstore.yes24.book.dto.BookResponseDto;
import com.bookstore.yes24.order.dto.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order orderUpdateDtoToOrder(OrderUpdateDto orderUpdateDto);

    OrderResponseDto orderToOrderResponseDto(Order order, List<BookResponseDto> bookList);

    List<OrderResponseDto> orderListToOrderResponseDtoList(List<Order> orderList);

}
