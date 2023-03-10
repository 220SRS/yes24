package com.bookstore.yes24.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookResponseDto {

    private Long bookId;

    private String title;

    private String author;

    private int price;

    private int quantity;
}
