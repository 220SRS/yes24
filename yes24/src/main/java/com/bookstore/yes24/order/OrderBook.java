package com.bookstore.yes24.order;

import com.bookstore.yes24.book.Book;
import com.bookstore.yes24.order.dto.OrderBookDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderBookId;

    private int quantity;


    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    private Book book;


    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;


    public void setBook(Book book) {
        this.book = book;
        book.getOrderBookList().add(this);
    }

}
