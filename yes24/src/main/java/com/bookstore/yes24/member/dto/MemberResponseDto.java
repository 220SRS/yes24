package com.bookstore.yes24.member.dto;

import com.bookstore.yes24.book.dto.BookResponseDto;
import com.bookstore.yes24.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@NoArgsConstructor
@Setter
@Getter
public class MemberResponseDto {

    private String nickName;

    private String email;

    public static MemberResponseDto of(Member member) {

        MemberResponseDto memberResponseDto = new MemberResponseDto();

        memberResponseDto.setNickName(member.getNickName());
        memberResponseDto.setEmail(member.getEmail());

        return memberResponseDto;
    }
    
    public static List<MemberResponseDto> ofList(List<Member> list) {

        List<MemberResponseDto> memberResponseDtoList = new ArrayList<>();

        for (Member member : list) {
                memberResponseDtoList.add(MemberResponseDto.of(member));
        }

        return memberResponseDtoList;
    }
}
