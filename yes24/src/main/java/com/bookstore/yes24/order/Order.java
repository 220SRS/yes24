package com.bookstore.yes24.order;

import com.bookstore.yes24.member.Member;
import com.bookstore.yes24.member.MemberRepository;
import com.bookstore.yes24.order.dto.OrderBookDto;
import com.bookstore.yes24.order.dto.OrderCreateDto;
import com.bookstore.yes24.order.dto.OrderUpdateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ORDERS")
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;

    private String orderNumber = RandomStringUtils.randomAlphanumeric(8);

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderBook> orderBooks = new ArrayList<>();

}
