package com.bookstore.yes24.order;

import com.bookstore.yes24.book.Book;
import com.bookstore.yes24.book.BookMapper;
import com.bookstore.yes24.book.BookRepository;
import com.bookstore.yes24.book.dto.BookResponseDto;
import com.bookstore.yes24.order.dto.OrderCreateDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    private final OrderFactory orderFactory;

    private final BookMapper bookMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper, OrderFactory orderFactory, BookMapper bookMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.orderFactory = orderFactory;
        this.bookMapper = bookMapper;
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        Order order = orderService.createOrder(orderFactory.assemblingTheOrder(orderCreateDto));

        List<BookResponseDto> bookList = bookMapper.BookToBookResponseDtoList(
                order.getOrderBooks().stream()
                        .map(OrderBook::getBook)
                        .collect(Collectors.toList())
        );


        return new ResponseEntity(
                orderMapper.orderToOrderResponseDto(order, bookList),
                HttpStatus.CREATED);
    }

//    @PatchMapping("/{order-id}")
//    public ResponseEntity updateOrder(@PathVariable("order-id") Long orderId,
//                                      @RequestBody OrderUpdateDto orderUpdateDto){
//        Order order = orderService.updateOrder(orderId, orderMapper.orderUpdateDtoToOrder(orderUpdateDto));
//
//        return new ResponseEntity(orderMapper.orderToOrderResponseDto(order), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{order-id}")
//    public ResponseEntity deleteOrder(@PathVariable("order-id") Long orderId) {
//        orderService.deleteOrder(orderId);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
