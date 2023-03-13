package com.bookstore.yes24.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(NullPointerException::new);
    }

    public Page<Order> findOrderList(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size, Sort.by("orderId").descending()));
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }


    public Order updateOrder(Long orderId, Order order) {
        Order findOrder = orderRepository.findById(orderId).orElseThrow(NullPointerException::new);

        Optional.ofNullable(order.getOrderStatus())
                .ifPresent(orderStatus -> findOrder.setOrderStatus(orderStatus));

        return orderRepository.save(findOrder);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

}
