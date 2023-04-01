package com.bookstore.yes24.order;

import com.bookstore.yes24.book.Book;
import com.bookstore.yes24.book.BookRepository;
import com.bookstore.yes24.member.Member;
import com.bookstore.yes24.member.MemberRepository;
import com.bookstore.yes24.member.MemberService;
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

    private final MemberService memberService;
    private final OrderRepository orderRepository;

    private final BookRepository bookRepository;

    public OrderService(MemberRepository memberRepository, MemberService memberService, OrderRepository orderRepository, BookRepository bookRepository) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    public OrderResponseDto findOrder(Long orderId) {

        Order order = orderRepository.findById(orderId).orElseThrow(IllegalArgumentException::new);

        return OrderResponseDto.of(order);
    }

    public MultiResponseDto<OrderResponseDto> findOrderList(int page, int size) {

        Page<Order> orderPage = orderRepository.findAll(PageRequest.of(page, size, Sort.by("orderId").descending()));
        List<Order> orderList = orderPage.getContent();

        return new MultiResponseDto<>(OrderResponseDto.ofList(orderList), orderPage);
    }


    @Transactional
    public OrderResponseDto createOrder(OrderCreateDto orderCreateDto) {

        Member member = memberService.findMemberEntity(orderCreateDto.getMemberId());

        Order order = Order.of(member, orderCreateDto);

        orderRepository.save(order);

        return OrderResponseDto.of(order);
    }

    @Transactional
    public OrderResponseDto updateOrder(OrderUpdateDto orderUpdateDto) {

        Order findOrder = orderRepository.findById(orderUpdateDto.getOrderId()).orElseThrow(IllegalArgumentException::new);

        findOrder.setOrderStatus(orderUpdateDto.getOrderStatus());

        return OrderResponseDto.of(findOrder);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
