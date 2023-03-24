package com.bookstore.yes24.order.dto.response;

import com.bookstore.yes24.book.Book;
import com.bookstore.yes24.order.OrderBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OrderBookResponseDto {

    private String title;

    private String author;

    private Integer price;

    private Integer orderQuantity;



    private static OrderBookResponseDto of(Book book, Integer orderQuantity) {

        OrderBookResponseDto orderBookResponseDto = new OrderBookResponseDto();

        orderBookResponseDto.setTitle(book.getTitle());
        orderBookResponseDto.setAuthor(book.getAuthor());
        orderBookResponseDto.setPrice(book.getPrice());
        orderBookResponseDto.setOrderQuantity(orderQuantity);

        return orderBookResponseDto;
    }

    public static List<OrderBookResponseDto> ofList(List<OrderBook> orderBooks) {

        List<OrderBookResponseDto> list = new ArrayList<>();

        for(OrderBook orderBook : orderBooks) {
            Book book = orderBook.getBook();
            list.add(OrderBookResponseDto.of(book,orderBook.getQuantity()));
        }

        return list;
    }
}
