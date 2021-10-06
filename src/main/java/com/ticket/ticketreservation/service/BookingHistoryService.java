package com.ticket.ticketreservation.service;

import com.ticket.ticketreservation.dto.BookingHistoryRequestDto;
import com.ticket.ticketreservation.dto.BookingRequestDto;
import com.ticket.ticketreservation.repository.BookingHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingHistoryService {

    private final BookingHistoryRepository bookingHistoryRepository;
    private final PerformanceService performanceService;
    private final MemberService memberService;

    public BookingHistoryService(BookingHistoryRepository bookingHistoryRepository, PerformanceService performanceService, MemberService memberService) {
        this.bookingHistoryRepository = bookingHistoryRepository;
        this.performanceService = performanceService;
        this.memberService = memberService;
    }

    /* 공연 예약 로그 저장 */
    private void saveBookingHistory(BookingHistoryRequestDto bookingHistoryRequestDto){
        bookingHistoryRepository.save(bookingHistoryRequestDto.toEntity());
    }

    /* 공연 예약 성공로그 저장 */
    public void saveSuccessLog(BookingRequestDto bookingRequestDto){
        BookingHistoryRequestDto bookingHistoryRequestDto = BookingHistoryRequestDto.builder()
                .performance(performanceService.showPerformanceInfo(bookingRequestDto.getTitle(), bookingRequestDto.getPerformanceDate()
                        , bookingRequestDto.getPerformanceDate()).toEntity())
                .member(memberService.findByMemberEmail(bookingRequestDto.getMemberEmail()).toEntity())
                .seatType(bookingRequestDto.getSeatType())
                .seatNumber(bookingRequestDto.getSeatNumber())
                .performanceDate(bookingRequestDto.getPerformanceDate())
                .price(bookingRequestDto.getPrice())
                .isBooking(true)
                .build();
        saveBookingHistory(bookingHistoryRequestDto);
    }

    /* 공연 예약 실패로그 저장 */
    public void saveFailLog(BookingRequestDto bookingRequestDto){
        BookingHistoryRequestDto bookingHistoryRequestDto = BookingHistoryRequestDto.builder()
                .performance(performanceService.showPerformanceInfo(bookingRequestDto.getTitle(), bookingRequestDto.getPerformanceDate()
                        , bookingRequestDto.getPerformanceDate()).toEntity())
                .member(memberService.findByMemberEmail(bookingRequestDto.getMemberEmail()).toEntity())
                .seatType(bookingRequestDto.getSeatType())
                .seatNumber(bookingRequestDto.getSeatNumber())
                .performanceDate(bookingRequestDto.getPerformanceDate())
                .price(bookingRequestDto.getPrice())
                .build();
        saveBookingHistory(bookingHistoryRequestDto);
    }
}