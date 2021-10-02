package com.ticket.ticketreservation.repository;

import com.ticket.ticketreservation.domain.Performance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {

    /* 해당 날짜의 특정 공연 정보 조회(날짜 오름차순) */
    @Query("select p from Performance p where p.title=?1 and (p.startDate>=?2 or p.endDate>=?3) order by p.startDate asc")
    List<Performance> findPerformanceList(String title, LocalDate startDate, LocalDate endDate);
}