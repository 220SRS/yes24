package com.bookstore.yes24.book.dto;

import com.bookstore.yes24.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class BookResponseDto {

    private Long bookId;

    private String title;

    private String author;

    private int price;

    private int quantity;

    public static BookResponseDto of(Book book) {
        BookResponseDto dto = new BookResponseDto();
        dto.bookId = book.getBookId();
        // ...

        return dto;
    }
}
