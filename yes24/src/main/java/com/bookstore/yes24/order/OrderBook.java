package com.bookstore.yes24.order;

import com.bookstore.yes24.book.Book;
import com.bookstore.yes24.order.dto.OrderBookDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static void of(Order order, List<OrderBookDto> orderBookList) {
        orderBookList.stream()
                .map(orderBookDto -> {
                    OrderBook orderBook = new OrderBook();
                    orderBook.setQuantity(orderBookDto.getQuantity());
                    orderBook.setBook(Book.bookWithIdOnly(orderBookDto.getBookId()));
                })
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void setBook(Book book) {
        this.book = book;
        book.getOrderBookList().add(this);
    }

}
