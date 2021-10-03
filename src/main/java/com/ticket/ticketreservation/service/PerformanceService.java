package com.ticket.ticketreservation.service;

import com.ticket.ticketreservation.domain.Performance;
import com.ticket.ticketreservation.dto.PerformanceDto;
import com.ticket.ticketreservation.exception.customException.ResourceNotFoundException;
import com.ticket.ticketreservation.repository.PerformanceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PerformanceService {
    private final PerformanceRepository performanceRepository;

    public PerformanceService(PerformanceRepository performanceRepository) {
        this.performanceRepository = performanceRepository;
    }

    /* 특정 날짜에서 예약가능한 특정 공연들 정보 조회(날짜 오름차순) */
    public List<PerformanceDto> showPerformanceList(String title, LocalDate date){
        List<Performance> performanceList = performanceRepository.findPerformanceList(title, date, date);
        if (performanceList.isEmpty()) {
            throw new ResourceNotFoundException("공연 조회 결과 없음");
        }

        List<PerformanceDto> performanceDtoList = new ArrayList<>();
        for(Performance performance : performanceList){
            performanceDtoList.add(PerformanceDto.from(performance));
        }
        return performanceDtoList;
    }

    /* 특정 날짜의 특정 공연 정보 조회 */
    public PerformanceDto showPerformanceInfo(String title, LocalDate startDate, LocalDate endDate){
        Optional<Performance> performance = performanceRepository.findByTitleAndStartDateLessThanEqualAndEndDateGreaterThanEqual(title, startDate, endDate);
        return PerformanceDto.from(performance.orElseThrow(() -> new ResourceNotFoundException("공연 조회 결과 없음")));
    }
}