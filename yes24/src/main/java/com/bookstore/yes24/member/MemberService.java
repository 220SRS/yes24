package com.bookstore.yes24.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    public Member updateMember(Long memberId, Member member) {
        Member findMember = memberRepository.findById(memberId).orElseThrow(NullPointerException::new);

        Optional.ofNullable(member.getMemberName())
                .ifPresent(findMember::setMemberName);

        Optional.ofNullable(member.getNickName())
                .ifPresent(findMember::setNickName);

        Optional.ofNullable(member.getBirthDate())
                .ifPresent(findMember::setBirthDate);

        Optional.ofNullable(member.getEmail())
                .ifPresent(findMember::setEmail);

        Optional.ofNullable(member.getPhone())
                .ifPresent(findMember::setPhone);

        return memberRepository.save(findMember);
    }

    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }
}
