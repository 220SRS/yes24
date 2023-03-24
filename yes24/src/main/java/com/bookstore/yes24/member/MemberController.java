package com.bookstore.yes24.member;


import com.bookstore.yes24.member.dto.MemberCreateDto;
import com.bookstore.yes24.member.dto.MemberCreateResponseDto;
import com.bookstore.yes24.member.dto.MemberResponseDto;
import com.bookstore.yes24.member.dto.MemberUpdateDto;
import com.bookstore.yes24.pageResponse.MultiResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;


@RestController
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{member-id}")
    public ResponseEntity<MemberResponseDto> getMember(@PathVariable("member-id") Long memberId) {

        MemberResponseDto memberResponseDto = memberService.findMember(memberId);

        return ResponseEntity.ok(memberResponseDto);
    }

    @GetMapping("/memberName/{member-name}")
    public ResponseEntity<MultiResponseDto<MemberResponseDto>> getMemberName(@PathVariable("member-name") String memberName,
                                        @RequestParam int page,
                                        @RequestParam int size) {

        MultiResponseDto<MemberResponseDto> multiResponseDto = memberService.findMemberNameList(memberName, page-1, size);

        return ResponseEntity.ok(multiResponseDto);
    }

    @GetMapping
    public ResponseEntity<MultiResponseDto<MemberResponseDto>> getMemberList(@RequestParam int page,
                                        @RequestParam int size) {

        MultiResponseDto<MemberResponseDto> multiResponseDto = memberService.findMemberList(page-1, size);

        return ResponseEntity.ok(multiResponseDto);
    }

    @PostMapping
    public ResponseEntity<MemberCreateResponseDto> creataMember(@RequestBody MemberCreateDto memberCreateDto) {

        MemberCreateResponseDto memberCreateResponseDto = memberService.createMember(memberCreateDto);

        return ResponseEntity.ok(memberCreateResponseDto);
    }

    @PatchMapping
    public ResponseEntity<MemberCreateResponseDto> updateMember(@RequestBody MemberUpdateDto memberUpdateDto) {

        MemberCreateResponseDto memberCreateResponseDto = memberService.updateMember(memberUpdateDto);

        return ResponseEntity.ok(memberCreateResponseDto);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity<Null> deleteMember(@PathVariable("member-id") Long memberId) {

        memberService.deleteMember(memberId);

        return ResponseEntity.ok(null);
    }

}
