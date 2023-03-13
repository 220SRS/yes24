package com.bookstore.yes24.order;

import com.bookstore.yes24.book.Book;
import com.bookstore.yes24.book.BookMapper;
import com.bookstore.yes24.book.BookRepository;
import com.bookstore.yes24.book.dto.BookResponseDto;
import com.bookstore.yes24.order.dto.OrderCreateDto;
import com.bookstore.yes24.order.dto.OrderUpdateDto;
import com.bookstore.yes24.pageResponse.MultiResponseDto;
import org.springframework.data.domain.Page;
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

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") Long orderId) {
        Order findOrder = orderService.findOrder(orderId);

        return new ResponseEntity(orderMapper.orderToOrderResponseDto(findOrder), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrderList(@RequestParam int page, @RequestParam int size) {
        Page<Order> pageOrderList = orderService.findOrderList(page-1, size);
        List<Order> orderList = pageOrderList.getContent();

        return new ResponseEntity(
                new MultiResponseDto<>(orderMapper.orderListToOrderResponseDtoList(orderList), pageOrderList),
                HttpStatus.OK
        );
    }


    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderCreateDto orderCreateDto) {
        Order order = orderService.createOrder(orderFactory.assemblingTheOrder(orderCreateDto));

        return new ResponseEntity(orderMapper.orderToOrderResponseDto(order), HttpStatus.CREATED);
    }

    @PatchMapping("/{order-id}")
    public ResponseEntity updateOrder(@PathVariable("order-id") Long orderId,
                                      @RequestBody OrderUpdateDto orderUpdateDto){
        Order updateOrder = orderService.updateOrder(orderId, orderMapper.orderUpdateDtoToOrder(orderUpdateDto));

        return new ResponseEntity(orderMapper.orderToOrderResponseDto(updateOrder), HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity deleteOrder(@PathVariable("order-id") Long orderId) {
        orderService.deleteOrder(orderId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
