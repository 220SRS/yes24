package com.bookstore.yes24.book;


import com.bookstore.yes24.order.OrderBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
}
