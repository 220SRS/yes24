package com.bookstore.yes24.order.dto;

import com.bookstore.yes24.book.dto.BookResponseDto;
import com.bookstore.yes24.order.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderResponseDto {

    private Long orderId;

    private List<BookResponseDto> bookList;

    private OrderStatus orderStatus;

}
