package com.ticket.ticketreservation.dto;

import com.ticket.ticketreservation.domain.Booking;
import com.ticket.ticketreservation.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponseDto {

    private Long bookingId;
    private String title;
    private String memberEmail;
    private SeatType seatType;
    private int seatNumber;
    private LocalDate performanceDate;
    private int price;
    private Date createAt;

    public static BookingResponseDto from(Booking booking){
        return BookingResponseDto.builder()
                .bookingId(booking.getBookingId())
                .title(booking.getPerformance().getTitle())
                .memberEmail(booking.getMember().getMemberEmail())
                .seatType(booking.getSeatType())
                .seatNumber(booking.getSeatNumber())
                .performanceDate(booking.getPerformanceDate())
                .price(booking.getPrice())
                .createAt(booking.getCreateAt())
                .build();
    }
}