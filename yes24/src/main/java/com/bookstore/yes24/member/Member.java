package com.bookstore.yes24.member;

import com.bookstore.yes24.member.dto.MemberUpdateDto;
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
import java.util.Optional;

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

    // JPA N+1
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Order> orderList = new ArrayList<>();

    // dto, 하나씩 받는 경우
    public void update(MemberUpdateDto dto) {
        if (dto.getMemberId() != null)
            memberId = dto.getMemberId();

        // TODO
        memberName = dto.getMemberName();

        nickName = dto.getNickName();

        email = dto.getEmail();

        phone = dto.getPhone();

    }
}
