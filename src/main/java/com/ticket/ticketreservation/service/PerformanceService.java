package com.ticket.ticketreservation.service;

import com.ticket.ticketreservation.domain.Performance;
import com.ticket.ticketreservation.repository.PerformanceRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    public PerformanceService(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    /* 해당 날짜의 특정 공연 정보 조회(날짜 오름차순) */
    public List<Performance> showPerformanceInfo(String title, Date startDate, Date endDate){
        return performanceRepository.findPerformanceList(title, startDate, endDate);
    }
}