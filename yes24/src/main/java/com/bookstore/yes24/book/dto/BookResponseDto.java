package com.bookstore.yes24.book.dto;

import com.bookstore.yes24.book.Book;
import com.bookstore.yes24.member.Member;
import com.bookstore.yes24.member.dto.MemberResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    public static List<BookResponseDto> ofList(List<Book> list) {

        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();

        for (Book book : list) {
            bookResponseDtoList.add(BookResponseDto.of(book));
        }

        return bookResponseDtoList;
    }
}
