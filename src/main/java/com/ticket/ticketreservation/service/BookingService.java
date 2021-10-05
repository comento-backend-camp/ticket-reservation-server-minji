package com.ticket.ticketreservation.service;

import com.ticket.ticketreservation.domain.Booking;
import com.ticket.ticketreservation.dto.*;
import com.ticket.ticketreservation.enums.SeatType;
import com.ticket.ticketreservation.exception.customException.AlreadyExistsException;
import com.ticket.ticketreservation.exception.customException.ResourceNotFoundException;
import com.ticket.ticketreservation.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private static final int MAX_SEAT_NUMBER = 20;

    private final BookingRepository bookingRepository;
    private final PerformanceService performanceService;
    private final MemberService memberService;
    private final BookingHistoryService bookingHistoryService;

    public BookingService(BookingRepository bookingRepository, PerformanceService performanceService, MemberService memberService, BookingHistoryService bookingHistoryService) {
        this.bookingRepository = bookingRepository;
        this.performanceService = performanceService;
        this.memberService = memberService;
        this.bookingHistoryService = bookingHistoryService;
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
        return bookingList.stream()
                .map(BookingResponseDto::from)
                .collect(Collectors.toList());
    }

    /* 공연 예약 */
    public BookingResponseDto saveBooking(BookingRequestDto bookingRequestDto){
        MemberDto memberDto = memberService.findByMemberEmail(bookingRequestDto.getMemberEmail());
        PerformanceDto performanceDto = performanceService.showPerformanceInfo(bookingRequestDto.getTitle(), bookingRequestDto.getPerformanceDate(), bookingRequestDto.getPerformanceDate());

        isDuplicatedSeatException(bookingRequestDto, performanceDto);
        isValidSeatNumberException(bookingRequestDto);
        return BookingResponseDto.from(bookingRepository.save(bookingRequestDto.toEntity(performanceDto.toEntity(), memberDto.toEntity())));
    }

    /* 좌석 중복 예외처리 */
    private void isDuplicatedSeatException(BookingRequestDto bookingRequestDto, PerformanceDto performanceDto) {
        if(isDuplicatedSeat(bookingRequestDto, performanceDto)){
            bookingHistoryService.saveFailLog(bookingRequestDto);
            throw new AlreadyExistsException("좌석 중복");
        }
    }

    /* 존재하지 않은 좌석 예외 처리 */
    private void isValidSeatNumberException(BookingRequestDto bookingRequestDto) {
        if(bookingRequestDto.getSeatNumber() > MAX_SEAT_NUMBER){
            bookingHistoryService.saveFailLog(bookingRequestDto);
            throw new ResourceNotFoundException("존재하지 않는 좌석");
        }
    }

    /* 좌석 중복 확인 */
    public boolean isDuplicatedSeat(BookingRequestDto bookingRequestDto, PerformanceDto performanceDto){
        return bookingRepository.findByPerformanceAndPerformanceDateAndSeatTypeAndSeatNumber(performanceDto.toEntity(), bookingRequestDto.getPerformanceDate(),
                bookingRequestDto.getSeatType(), bookingRequestDto.getSeatNumber()).isPresent();
    }
}