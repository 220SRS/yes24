package com.bookstore.yes24.book.dto;

import com.bookstore.yes24.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookResponseDto {

    private String title;

    private String author;

    private int price;

    public static BookResponseDto of(Book book) {

        BookResponseDto dto = new BookResponseDto();
        dto.setTitle(book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        return dto;
    }
}
