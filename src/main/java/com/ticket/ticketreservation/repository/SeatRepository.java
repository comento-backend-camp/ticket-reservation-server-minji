package com.ticket.ticketreservation.repository;

import com.ticket.ticketreservation.domain.Performance;
import com.ticket.ticketreservation.domain.Seat;
import com.ticket.ticketreservation.enums.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {

    /* 특정영화의 특정날짜에 특정좌석유형으로 예약된 좌석 조회*/
    List<Seat> findByPerformanceAndPerformanceDateAndSeatType(Performance performance, LocalDate performanceDate, SeatType seatType);
}