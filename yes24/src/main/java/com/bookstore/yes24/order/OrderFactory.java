package com.bookstore.yes24.order;

import com.bookstore.yes24.book.BookService;
import com.bookstore.yes24.member.Member;
import com.bookstore.yes24.member.MemberService;
import com.bookstore.yes24.order.dto.OrderCreateDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderFactory {

    private final MemberService memberService;

    private final BookService bookService;

    public OrderFactory(MemberService memberService, BookService bookService) {
        this.memberService = memberService;
        this.bookService = bookService;
    }

    public Order assemblingTheOrder(OrderCreateDto orderCreateDto) {
        if (orderCreateDto == null) {
            return null;
        } else {
            Order order = new Order();
            order.setMember(memberService.findMember(orderCreateDto.getMemberId()));
            List<OrderBook> orderBookList = new ArrayList<>();

            orderCreateDto.getOrderBookList().stream()
                            .forEach(orderBookDto -> {
                                OrderBook orderBook = new OrderBook();
                                orderBook.setQuantity(orderBookDto.getQuantity());
                                orderBook.setOrder(order);
//                                orderBook.setBook(
//                                        bookService.findBook(orderBookDto.getBookId())
//                                );
                                orderBookList.add(orderBook);
                            });

            order.setOrderBooks(orderBookList);
            return order;
        }
    }

}
