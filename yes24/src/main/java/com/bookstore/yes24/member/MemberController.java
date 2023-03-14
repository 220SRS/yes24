package com.bookstore.yes24.member;


import com.bookstore.yes24.member.dto.MemberCreateDto;
import com.bookstore.yes24.member.dto.MemberUpdateDto;
import com.bookstore.yes24.pageResponse.MultiResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @GetMapping("/{member-id}")
    public ResponseEntity getMember(@PathVariable("member-id") Long memberId) {

        Member member = memberService.findMember(memberId);

        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(member),
                HttpStatus.OK);
    }

    @GetMapping("/memberName/{member-name}")
    public ResponseEntity getMemberName(@PathVariable("member-name") String memberName,
                                        @RequestParam int page,
                                        @RequestParam int size) {
        Page<Member> pageMemberNameList = memberService.findMemberNameList(memberName, page-1, size);
        List<Member> memberNameList = pageMemberNameList.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(memberMapper.memberListToMemberResponseDtoList(memberNameList), pageMemberNameList),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMemberName(@RequestParam int page,
                                        @RequestParam int size) {
        Page<Member> pageMemberList = memberService.findMemberList(page-1, size);
        List<Member> memberList = pageMemberList.getContent();

        return new ResponseEntity<>(
                new MultiResponseDto<>(memberMapper.memberListToMemberResponseDtoList(memberList), pageMemberList
                        ),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity creataMember(@RequestBody MemberCreateDto memberCreateDto) {

        Member member = memberService.createMember(
                memberMapper.memberCreateDtoToMember(memberCreateDto)
        );

        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(member),
                HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity updateMember(@RequestBody MemberUpdateDto memberUpdateDto) {

        Member updateMember = memberService.updateMember(memberUpdateDto);

        return new ResponseEntity<>(memberMapper.memberToMemberResponseDto(updateMember),
                HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(@PathVariable("member-id") Long memberId) {
        memberService.deleteMember(memberId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
