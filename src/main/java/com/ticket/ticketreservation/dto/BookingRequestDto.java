package com.ticket.ticketreservation.dto;

import com.ticket.ticketreservation.domain.Booking;
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
public class BookingRequestDto {

    private String title;
    private String memberEmail;
    private SeatType seatType;
    private int seatNumber;
    private LocalDate performanceDate;
    private int price;

    public Booking toEntity(Performance performance, Member member){
        return Booking.builder()
                .performance(performance)
                .member(member)
                .seatType(seatType)
                .seatNumber(seatNumber)
                .performanceDate(performanceDate)
                .price(price)
                .build();
    }
}