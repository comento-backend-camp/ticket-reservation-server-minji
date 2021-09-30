package com.ticket.ticketreservation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;

    @NotEmpty
    @Email
    @Column(name = "MEMBER_EMAIL")
    private String memberEmail;

    public Member(String memberEmail) {
        this.memberEmail = memberEmail;
    }
}
