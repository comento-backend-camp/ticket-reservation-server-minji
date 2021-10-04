package com.ticket.ticketreservation.dto;

import com.ticket.ticketreservation.domain.BookingHistory;
import com.ticket.ticketreservation.domain.Member;
import com.ticket.ticketreservation.domain.Performance;
import com.ticket.ticketreservation.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingHistoryRequestDto {

    private Performance performance;
    private Member member;
    private SeatType seatType;
    private int seatNumber;
    private LocalDate performanceDate;
    private int price;
    private boolean isBooking;

    public BookingHistory toEntity(){
        return BookingHistory.builder()
                .performance(performance)
                .member(member)
                .seatType(seatType)
                .seatNumber(seatNumber)
                .performanceDate(performanceDate)
                .price(price)
                .isBooking(isBooking)
                .build();
    }
}