package com.ticket.ticketreservation.service;

import com.ticket.ticketreservation.domain.Member;
import com.ticket.ticketreservation.exception.customException.AlreadyExistsException;
import com.ticket.ticketreservation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /* 회원가입(이메일 등록) */
    public void save(Member member){

        if(emailDuplicateCheck(member.getMemberEmail())){
            throw new AlreadyExistsException("MemberService");
        }
        memberRepository.save(member);
    }

    /* 이메일 중복 체크 */
    public boolean emailDuplicateCheck(String memberEmail){
        return memberRepository.findByMemberEmail(memberEmail).isPresent();
    }
}
