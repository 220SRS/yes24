package com.bookstore.yes24.book;

import com.bookstore.yes24.book.dto.BookCreateDto;
import com.bookstore.yes24.book.dto.BookResponseDto;
import com.bookstore.yes24.book.dto.BookUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book BookcreatDtoToBook (BookCreateDto bookCreateDto);

    Book BookupdateDtoToBook (BookUpdateDto bookUpdateDto);

    BookResponseDto BookToBookResponseDto(Book book);

    List<BookResponseDto> BookToBookResponseDtoList(List<Book> bookList);
}
