package com.ticket.ticketreservation.service;

import com.ticket.ticketreservation.dto.BookingHistoryRequestDto;
import com.ticket.ticketreservation.repository.BookingHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingHistoryService {

    private final BookingHistoryRepository bookingHistoryRepository;
    private final MemberService memberService;
    private final PerformanceService performanceService;

    public BookingHistoryService(BookingHistoryRepository bookingHistoryRepository, MemberService memberService, PerformanceService performanceService) {
        this.bookingHistoryRepository = bookingHistoryRepository;
        this.memberService = memberService;
        this.performanceService = performanceService;
    }

    public void saveBookingHistory(BookingHistoryRequestDto bookingHistoryRequestDto){
        bookingHistoryRepository.save(bookingHistoryRequestDto.toEntity());
    }
}