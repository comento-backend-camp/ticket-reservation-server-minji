package com.ticket.ticketreservation.service;

import com.ticket.ticketreservation.domain.Booking;
import com.ticket.ticketreservation.domain.Performance;
import com.ticket.ticketreservation.enums.SeatType;
import com.ticket.ticketreservation.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PerformanceService performanceService;
    public BookingService(BookingRepository bookingRepository, PerformanceService performanceService) {
        this.bookingRepository = bookingRepository;
        this.performanceService = performanceService;
    }

    /* 특정 공연의 특정날짜에 예약된 좌석 조회 */
    public HashMap<String, List<Integer>> showReservedSeats(String title, LocalDate date){
        Performance performance = performanceService.showPerformanceInfo(title, date, date);
        HashMap<String, List<Integer>> reservedSeatMap = new HashMap<>();

        for(SeatType type : SeatType.values()){
            List<Booking> seatTypeList = bookingRepository.findByPerformanceAndPerformanceDateAndSeatType(performance, date, type);
            List<Integer> seatNumberList = new ArrayList<>();
            for(int i=0; i<seatTypeList.size(); i++){
                seatNumberList.add(seatTypeList.get(i).getSeatNumber());
            }
            reservedSeatMap.put(type.name(), seatNumberList);
        }
        return reservedSeatMap;
    }
}