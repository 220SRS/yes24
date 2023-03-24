package com.bookstore.yes24.order;

import com.bookstore.yes24.order.dto.OrderCreateDto;
import com.bookstore.yes24.order.dto.response.OrderResponseDto;
import com.bookstore.yes24.order.dto.OrderUpdateDto;
import com.bookstore.yes24.pageResponse.MultiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable("order-id") Long orderId) {

        OrderResponseDto orderResponseDto = orderService.findOrder(orderId);

        return ResponseEntity.ok(orderResponseDto);
    }

    @GetMapping
    public ResponseEntity<MultiResponseDto<OrderResponseDto>> getOrderList(@RequestParam int page, @RequestParam int size) {

        MultiResponseDto<OrderResponseDto> multiResponseDto = orderService.findOrderList(page-1,size);

        return ResponseEntity.ok(multiResponseDto);
    }


    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderCreateDto orderCreateDto) {

        OrderResponseDto orderResponseDto = orderService.createOrder(orderCreateDto);

        return ResponseEntity.ok(orderResponseDto);
    }

    @PatchMapping
    public ResponseEntity<OrderResponseDto> updateOrder(@RequestBody OrderUpdateDto orderUpdateDto){

        OrderResponseDto orderResponseDto = orderService.updateOrder(orderUpdateDto);

        return ResponseEntity.ok(orderResponseDto);
    }

    @DeleteMapping("/{order-id}")
    public ResponseEntity<Null> deleteOrder(@PathVariable("order-id") Long orderId) {

        orderService.deleteOrder(orderId);

        return ResponseEntity.ok(null);
    }
}
