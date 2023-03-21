package com.bookstore.yes24.book;


import com.bookstore.yes24.book.dto.BookCreateDto;
import com.bookstore.yes24.book.dto.BookUpdateDto;
import com.bookstore.yes24.order.OrderBook;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

        if (bookUpdateDto.getTitle() != null) {
            this.setTitle(bookUpdateDto.getTitle());
        }

        if (bookUpdateDto.getAuthor() != null) {
            this.setAuthor(bookUpdateDto.getAuthor());
        }

        if (bookUpdateDto.getPrice() != 0) {
            this.setPrice(bookUpdateDto.getPrice());
        }

        if (bookUpdateDto.getQuantity() != 0) {
            this.setQuantity(bookUpdateDto.getQuantity());
        }
    }
}
