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
public class MemberResponseDto {

    private Long memberId;
    private String memberEmail;

    public static MemberResponseDto from(Member member){
        return MemberResponseDto.builder()
                .memberId(member.getMemberId())
                .memberEmail(member.getMemberEmail())
                .build();
    }
}