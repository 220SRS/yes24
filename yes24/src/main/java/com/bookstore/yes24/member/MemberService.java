package com.bookstore.yes24.member;

import com.bookstore.yes24.member.dto.MemberCreateDto;
import com.bookstore.yes24.member.dto.MemberCreateResponseDto;
import com.bookstore.yes24.member.dto.MemberResponseDto;
import com.bookstore.yes24.member.dto.MemberUpdateDto;
import com.bookstore.yes24.pageResponse.MultiResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public MemberResponseDto findMember(Long memberId) {

        Member member = memberRepository.findById(memberId).orElseThrow(IllegalAccessError::new);

        return MemberResponseDto.of(member);
    }

    public MultiResponseDto<MemberResponseDto> findMemberNameList(String memberName, int page, int size) {

        Page<Member> memberPage = memberRepository.findByMemberName(memberName, PageRequest.of(page, size, Sort.by("memberId").descending()));

        List<Member> memberList = memberPage.getContent();

        return new MultiResponseDto<>(MemberResponseDto.ofList(memberList), memberPage);
    }

    public MultiResponseDto<MemberResponseDto> findMemberList(int page, int size) {
        Page<Member> memberPage = memberRepository.findAll(PageRequest.of(page, size, Sort.by("memberId").descending()));

        List<Member> memberList = memberPage.getContent();


        return new MultiResponseDto<>(MemberResponseDto.ofList(memberList), memberPage);
    }


    @Transactional
    public MemberCreateResponseDto createMember(MemberCreateDto memberCreateDto) {

        Member member = Member.of(memberCreateDto);

        memberRepository.save(member);

        return MemberCreateResponseDto.of(member);
    }


    @Transactional
    public MemberCreateResponseDto updateMember(MemberUpdateDto memberUpdateDto) {

        Member findMember = memberRepository.findById(memberUpdateDto.getMemberId()).orElseThrow(IllegalAccessError::new);

        findMember.update(memberUpdateDto);

        return MemberCreateResponseDto.of(findMember);
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
