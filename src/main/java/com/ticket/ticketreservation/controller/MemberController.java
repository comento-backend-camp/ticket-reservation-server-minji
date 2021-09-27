package com.ticket.ticketreservation.controller;

import com.ticket.ticketreservation.domain.Member;
import com.ticket.ticketreservation.exception.StatusCode;
import com.ticket.ticketreservation.exception.SuccessResponse;
import com.ticket.ticketreservation.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private StatusCode code;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /* 회원가입(이메일 등록) */
    @PostMapping("/join")
    public ResponseEntity join(@RequestBody @Valid String memberEmail){
        code = StatusCode.CREATED;
        memberService.save(memberEmail);
        return new ResponseEntity<>(SuccessResponse.of(code), code.getHttpStatus());
    }
}
