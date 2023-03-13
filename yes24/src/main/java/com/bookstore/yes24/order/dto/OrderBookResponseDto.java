package com.bookstore.yes24.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OrderBookResponseDto {

    private Long bookId;

    private String title;

    private String author;

    private Integer price;

    private Integer orderQuantity;

}
