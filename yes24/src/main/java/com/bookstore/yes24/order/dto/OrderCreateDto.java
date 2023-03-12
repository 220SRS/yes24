package com.bookstore.yes24.order.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateDto {

    private Long memberId;

    private List<OrderBookDto> orderBookList;

}
