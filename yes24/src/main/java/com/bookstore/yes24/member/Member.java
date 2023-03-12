package com.bookstore.yes24.member;

import com.bookstore.yes24.order.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String memberName;

    private String nickName;

    private LocalDate birthDate;

    private String email;

    private String phone;


    @OneToMany(mappedBy = "member")
    private List<Order> orderList = new ArrayList<>();
}
