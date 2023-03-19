package com.bookstore.yes24.book;


import com.bookstore.yes24.book.dto.BookCreateDto;
import com.bookstore.yes24.book.dto.BookUpdateDto;
import com.bookstore.yes24.order.OrderBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String title;

    private String author;

    private Integer price;

    private Integer quantity;


    @OneToMany(mappedBy = "book")
    private List<OrderBook> orderBookList = new ArrayList<>();

    public static Book of(BookCreateDto bookCreateDto){
        Book entity = new Book();

        entity.setTitle(bookCreateDto.getTitle());
        entity.setAuthor(bookCreateDto.getAuthor());
        entity.setPrice(bookCreateDto.getPrice());
        entity.setQuantity(bookCreateDto.getQuantity());

        return entity;
    }

    public void update(BookUpdateDto bookUpdateDto) {

        Optional.ofNullable(bookUpdateDto.getAuthor())
                .ifPresent(this::setAuthor);

        Optional.ofNullable(bookUpdateDto.getTitle())
                .ifPresent(this::setTitle);

        Optional.of(bookUpdateDto.getPrice())
                .filter(price -> price != 0)
                .ifPresent(this::setPrice);

        Optional.of(bookUpdateDto.getQuantity())
                .filter(quantity -> quantity != 0)
                .ifPresent(this::setQuantity);
    }
}
