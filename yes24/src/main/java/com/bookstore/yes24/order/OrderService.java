package com.bookstore.yes24.order;

import com.bookstore.yes24.book.Book;
import com.bookstore.yes24.book.BookRepository;
import com.bookstore.yes24.member.Member;
import com.bookstore.yes24.member.MemberRepository;
import com.bookstore.yes24.order.dto.OrderBookDto;
import com.bookstore.yes24.order.dto.OrderCreateDto;
import com.bookstore.yes24.order.dto.OrderUpdateDto;
import com.bookstore.yes24.order.dto.response.OrderResponseDto;
import com.bookstore.yes24.pageResponse.MultiResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final MemberRepository memberRepository;

    private final OrderRepository orderRepository;

    private final BookRepository bookRepository;

    public OrderService(MemberRepository memberRepository, OrderRepository orderRepository, BookRepository bookRepository) {
        this.memberRepository = memberRepository;
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    public OrderResponseDto findOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(IllegalAccessError::new);

        return OrderResponseDto.of(order);
    }

    public MultiResponseDto<OrderResponseDto> findOrderList(int page, int size) {

        Page<Order> orderPage = orderRepository.findAll(PageRequest.of(page, size, Sort.by("orderId").descending()));
        List<Order> orderList = orderPage.getContent();

        return new MultiResponseDto<>(OrderResponseDto.ofList(orderList), orderPage);
    }


    @Transactional
    public OrderResponseDto createOrder(OrderCreateDto orderCreateDto) {

        Order order = new Order();

        Member member =  memberRepository.findById(orderCreateDto.getMemberId()).orElseThrow(IllegalAccessError::new);
        order.setMember(member);


        List<OrderBook> orderBooks = new ArrayList<>();

        for (OrderBookDto orderBookDto : orderCreateDto.getOrderBookList()) {
            OrderBook orderBook = new OrderBook();

            Book book = bookRepository.findById(orderBookDto.getBookId()).orElseThrow(IllegalAccessError::new);

            orderBook.setBook(book);
            orderBook.setQuantity(orderBookDto.getQuantity());
            orderBook.setOrder(order);

            orderBooks.add(orderBook);
        }

        order.getOrderBooks().addAll(orderBooks);

        member.getOrderList().add(order);

        orderRepository.save(order);

        return OrderResponseDto.of(order);
    }

    @Transactional
    public OrderResponseDto updateOrder(OrderUpdateDto orderUpdateDto) {

        Order findOrder = orderRepository.findById(orderUpdateDto.getOrderId()).orElseThrow(IllegalAccessError::new);

        findOrder.setOrderStatus(orderUpdateDto.getOrderStatus());

        return OrderResponseDto.of(findOrder);

    }

    @Transactional
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
