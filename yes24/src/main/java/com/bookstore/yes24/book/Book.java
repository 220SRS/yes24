package com.bookstore.yes24.book;


import com.bookstore.yes24.book.dto.BookUpdateDto;
import com.bookstore.yes24.order.OrderBook;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public static Book of(String title, String author, Integer price, Integer quantity) {
        Book entity = new Book();
        entity.title = title;
        entity.author = author;
        entity.price = price;
        entity.quantity = quantity;
    }

    public void update(BookUpdateDto dto) {
        if (dto.getAuthor() != null)
            author = dto.getAuthor();

        // ... TODO
    }
}
