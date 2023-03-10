package com.bookstore.yes24.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;


@NoArgsConstructor
@Setter
@Getter
public class MemberResponseDto {

    private Long memberId;

    private String memberName;

    private String nickName;

    private LocalDate birthDate;

    private String email;

    private String phone;

}
