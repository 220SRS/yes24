package com.bookstore.yes24.member;

import com.bookstore.yes24.member.dto.MemberUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(NullPointerException::new);
    }


    public Page<Member> findMemberNameList(String memberName, int page, int size) {
        return memberRepository.findByMemberName(memberName,
                PageRequest.of(page, size, Sort.by("memberId").descending()));
    }


    public Page<Member> findMemberList(int page, int size) {
        return memberRepository.findAll(PageRequest.of(page, size,
                Sort.by("memberId").descending()));
    }


    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Transactional
    public Member updateMember(MemberUpdateDto dto) {
        Member findMember = memberRepository.findById(dto.getMemberId()).orElseThrow(NullPointerException::new);

        // update via dirty checking
        findMember.update(dto);
        return findMember;
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
