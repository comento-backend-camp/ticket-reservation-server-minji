package com.ticket.ticketreservation.dto;

import com.ticket.ticketreservation.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private Long memberId;
    private String memberEmail;

    public Member toEntity(){
        return Member.builder()
                .memberId(memberId)
                .memberEmail(memberEmail)
                .build();
    }

    public static MemberDto from(Member member){
        return MemberDto.builder()
                .memberId(member.getMemberId())
                .memberEmail(member.getMemberEmail())
                .build();
    }
}