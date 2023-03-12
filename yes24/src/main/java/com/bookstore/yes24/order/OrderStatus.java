package com.bookstore.yes24.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OrderStatus {
    ORDER_REQUEST(1, "주문접수"),

    ORDER_CONFIRM(2 , "상품준비중"),

    ORDER_PROCESING(3, "발송준비중"),

    ORDER_BEING_PACKED(4, "출고작업중"),

    ORDER_SHIPPED(5, "발송완료"),

    ORDER_DELIVERED(6, "배송완료"),

    ORDER_CANCLED(7, "주문취소");



    @Getter
    private int stepNumber;

    @Getter
    private String stepDescription;

}
