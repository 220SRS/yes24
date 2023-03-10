package com.bookstore.yes24.member.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class MemberUpdateDto {

    private Long memberId;

    private String memberName;

    private String nickName;

    private LocalDate birthDate;

    private String email;

    private String phone;

}
