package com.ticket.ticketreservation.repository;

import com.ticket.ticketreservation.domain.Booking;
import com.ticket.ticketreservation.domain.Member;
import com.ticket.ticketreservation.domain.Performance;
import com.ticket.ticketreservation.enums.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    /* 특정영화의 특정날짜에 특정좌석유형으로 예약된 좌석 조회*/
    List<Booking> findByPerformanceAndPerformanceDateAndSeatType(Performance performance, LocalDate performanceDate, SeatType seatType);

    /* 특정 유저의 예약정보 조회 */
    List<Booking> findByMember(Member member);

    /* 특정영화의 특정날짜에 특정좌석유형, 특정 좌석번호로 예약된 좌석 조회 */
    Optional<Booking> findByPerformanceAndPerformanceDateAndSeatTypeAndSeatNumber(Performance performance, LocalDate performanceDate, SeatType seatType, int SeatNumber);
}