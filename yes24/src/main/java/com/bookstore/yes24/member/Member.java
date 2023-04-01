package com.bookstore.yes24.member;

import com.bookstore.yes24.member.dto.MemberCreateDto;
import com.bookstore.yes24.member.dto.MemberUpdateDto;
import com.bookstore.yes24.order.Order;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String memberName;

    @Column(unique = true)
    private String nickName;

    private LocalDate birthDate;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<Order> orderList = new ArrayList<>();


    public static Member of(MemberCreateDto memberCreateDto) {

        Member member = new Member();
        member.setMemberName(memberCreateDto.getMemberName());
        member.setNickName(memberCreateDto.getNickName());
        member.setBirthDate(memberCreateDto.getBirthDate());
        member.setEmail(memberCreateDto.getEmail());
        member.setPhone(memberCreateDto.getPhone());

        return member;
    }

    public static Member memberWithIdOnly(Long memberId) {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }

    public void update(MemberUpdateDto memberUpdateDto) {

        if (memberUpdateDto.getMemberName() != null) {
            this.memberName = memberUpdateDto.getMemberName();
        }
        if (memberUpdateDto.getNickName() != null) {
            this.nickName = memberUpdateDto.getNickName();
        }
        if (memberUpdateDto.getBirthDate() != null) {
            this.birthDate = memberUpdateDto.getBirthDate();
        }
        if (memberUpdateDto.getEmail() != null) {
            this.email = memberUpdateDto.getEmail();
        }
        if (memberUpdateDto.getPhone() != null) {
            this.phone = memberUpdateDto.getPhone();
        }
    }
}
