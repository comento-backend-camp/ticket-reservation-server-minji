package com.ticket.ticketreservation.dto;

import com.ticket.ticketreservation.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookedSeatResponseDto {

    private SeatType seatType;
    private List<Integer> seatNumber;

    public static BookedSeatResponseDto from(SeatType seatType, List<Integer> seatNumberList){
        return BookedSeatResponseDto.builder()
                .seatType(seatType)
                .seatNumber(seatNumberList)
                .build();
    }
}