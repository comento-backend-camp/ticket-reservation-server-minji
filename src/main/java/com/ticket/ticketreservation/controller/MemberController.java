package com.ticket.ticketreservation.controller;

import com.ticket.ticketreservation.domain.Member;
import com.ticket.ticketreservation.exception.StatusCode;
import com.ticket.ticketreservation.exception.SuccessResponse;
import com.ticket.ticketreservation.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.net.URI;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /* 회원가입(이메일 등록) */
    @PostMapping("/join")
    public ResponseEntity join(@RequestBody @Valid String memberEmail){
        memberService.save(memberEmail);
        Optional<Member> member = memberService.findByMemberEmail(memberEmail);
        return ResponseEntity.created(URI.create("/members/" + memberEmail)).body(new SuccessResponse(StatusCode.CREATED, member));
    }

    /* 이메일 인증 */
    @GetMapping("/members/{memberEmail}")
    public ResponseEntity verifyEmail(@PathVariable(value = "memberEmail") @Email String memberEmail){
        Optional<Member> member = memberService.findByMemberEmail(memberEmail);
        return ResponseEntity.ok().body(new SuccessResponse(StatusCode.OK, member));
    }
}
