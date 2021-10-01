package com.ticket.ticketreservation.repository;

import com.ticket.ticketreservation.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    /* 이메일 조회 */
    Optional<Member> findByMemberEmail(String memberEmail);
}
