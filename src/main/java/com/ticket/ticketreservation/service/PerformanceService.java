package com.ticket.ticketreservation.service;

import com.ticket.ticketreservation.domain.Performance;
import com.ticket.ticketreservation.exception.customException.ResourceNotFoundException;
import com.ticket.ticketreservation.repository.PerformanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    public PerformanceService(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    /* 현재 날짜에 예약가능한 특정 공연 정보 조회(날짜 오름차순) */
    public List<Performance> showPerformanceInfo(String title){
        LocalDate today = LocalDate.now();
        List<Performance> performanceList = performanceRepository.findPerformanceList(title, today, today);
        if (performanceList.isEmpty()) {
            throw new ResourceNotFoundException("공연 조회 결과 없음");
        }
        return performanceRepository.findPerformanceList(title, today, today);
    }
}