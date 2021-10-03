package com.ticket.ticketreservation.controller;

import com.ticket.ticketreservation.dto.MemberResponseDto;
import com.ticket.ticketreservation.exception.StatusCode;
import com.ticket.ticketreservation.exception.SuccessResponse;
import com.ticket.ticketreservation.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.net.URI;

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
        MemberResponseDto memberDto = memberService.findByMemberEmail(memberEmail);
        return ResponseEntity.created(URI.create("/members/" + memberEmail)).body(new SuccessResponse(StatusCode.CREATED, memberDto));
    }

    /* 이메일 인증 */
    @GetMapping("/members/{memberEmail}")
    public ResponseEntity verifyEmail(@PathVariable(value = "memberEmail") @Email String memberEmail){
        MemberResponseDto memberDto = memberService.findByMemberEmail(memberEmail);
        return ResponseEntity.ok().body(new SuccessResponse(StatusCode.OK, memberDto));
    }
}
