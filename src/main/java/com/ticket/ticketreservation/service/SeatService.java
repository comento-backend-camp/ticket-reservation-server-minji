package com.ticket.ticketreservation.service;

import com.ticket.ticketreservation.domain.Performance;
import com.ticket.ticketreservation.domain.Seat;
import com.ticket.ticketreservation.enums.SeatType;
import com.ticket.ticketreservation.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final PerformanceService performanceService;
    public SeatService(SeatRepository seatRepository, PerformanceService performanceService) {
        this.seatRepository = seatRepository;
        this.performanceService = performanceService;
    }

    /* 특정 공연의 특정날짜에 예약된 좌석 조회 */
    public HashMap<String, List<Integer>> showReservedSeats(String title, LocalDate date){
        Performance performance = performanceService.showPerformanceInfo(title, date, date);
        HashMap<String, List<Integer>> reservedSeatMap = new HashMap<>();

        for(SeatType type : SeatType.values()){
            List<Seat> seatTypeList = seatRepository.findByPerformanceAndPerformanceDateAndSeatType(performance, date, type);
            List<Integer> seatNumberList = new ArrayList<>();
            for(int i=0; i<seatTypeList.size(); i++){
                seatNumberList.add(seatTypeList.get(i).getSeatNumber());
            }
            reservedSeatMap.put(type.name(), seatNumberList);
        }
        return reservedSeatMap;
    }
}