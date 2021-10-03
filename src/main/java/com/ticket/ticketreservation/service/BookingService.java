package com.ticket.ticketreservation.service;

import com.ticket.ticketreservation.domain.Booking;
import com.ticket.ticketreservation.dto.BookedSeatResponseDto;
import com.ticket.ticketreservation.dto.BookingResponseDto;
import com.ticket.ticketreservation.dto.MemberDto;
import com.ticket.ticketreservation.dto.PerformanceDto;
import com.ticket.ticketreservation.enums.SeatType;
import com.ticket.ticketreservation.exception.customException.ResourceNotFoundException;
import com.ticket.ticketreservation.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final PerformanceService performanceService;
    private final MemberService memberService;

    public BookingService(BookingRepository bookingRepository, PerformanceService performanceService, MemberService memberService) {
        this.bookingRepository = bookingRepository;
        this.performanceService = performanceService;
        this.memberService = memberService;
    }

    /* 특정 공연의 특정날짜에 예약된 좌석 조회 */
    public List<BookedSeatResponseDto> showBookedSeats(String title, LocalDate date){
        PerformanceDto performanceDto = performanceService.showPerformanceInfo(title, date, date);
        List<BookedSeatResponseDto> bookingList = new ArrayList();

        for(SeatType type : SeatType.values()){
            List<Booking> seatTypeList = bookingRepository.findByPerformanceAndPerformanceDateAndSeatType(performanceDto.toEntity(), date, type);
            List<Integer> seatNumberList = new ArrayList<>();
            for(int i=0; i<seatTypeList.size(); i++){
                seatNumberList.add(seatTypeList.get(i).getSeatNumber());
            }
            bookingList.add(BookedSeatResponseDto.from(type, seatNumberList));
        }
        return bookingList;
    }

    /* 공연 예약 정보 조회 */
    public List<BookingResponseDto> showBookingInfo(String memberEmail){
        MemberDto memberDto = memberService.findByMemberEmail(memberEmail);
        List<Booking> bookingList = bookingRepository.findByMember(memberDto.toEntity());
        if(bookingList.isEmpty()){
            throw new ResourceNotFoundException("공연 예약 정보 없음");
        }

        List<BookingResponseDto> bookingDtoList = new ArrayList<>();
        for(Booking booking : bookingList){
            bookingDtoList.add(BookingResponseDto.from(booking));
        }
        return bookingDtoList;
    }
}