package com.bookstore.yes24.book.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateDto {
    private Long bookId;

    private String title;

    private String author;

    private int price;

    private int quantity;
}
