package com.bookstore.yes24.member;

import com.bookstore.yes24.member.dto.MemberCreateDto;
import com.bookstore.yes24.member.dto.MemberResponseDto;
import com.bookstore.yes24.member.dto.MemberUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberCreateDtoToMember(MemberCreateDto memberCreateDto);

    Member memberUpdateDtoToMember(MemberUpdateDto memberUpdateDto);

    MemberResponseDto memberToMemberResponseDto(Member member);

    List<MemberResponseDto> memberListToMemberResponseDtoList(List<Member> memberList);
}
