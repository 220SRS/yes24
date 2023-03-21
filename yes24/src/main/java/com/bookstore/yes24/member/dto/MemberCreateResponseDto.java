package com.bookstore.yes24.member.dto;

import com.bookstore.yes24.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class MemberCreateResponseDto {
    private String memberName;

    private String nickName;

    private LocalDate birthDate;

    private String email;

    private String phone;


    public static MemberCreateResponseDto of(Member member) {

        MemberCreateResponseDto memberCreateResponseDto = new MemberCreateResponseDto();

        memberCreateResponseDto.setMemberName(member.getMemberName());
        memberCreateResponseDto.setNickName(member.getNickName());
        memberCreateResponseDto.setBirthDate(member.getBirthDate());
        memberCreateResponseDto.setEmail(member.getEmail());
        memberCreateResponseDto.setPhone(member.getPhone());

        return memberCreateResponseDto;
    }
}
