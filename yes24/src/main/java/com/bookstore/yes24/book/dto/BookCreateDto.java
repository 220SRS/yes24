package com.bookstore.yes24.book.dto;

import lombok.Getter;

@Getter
public class BookCreateDto {
    private String title;

    private String author;

    private int price;

    private int quantity;
}
