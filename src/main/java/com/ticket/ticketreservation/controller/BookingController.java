package com.ticket.ticketreservation.controller;

import com.ticket.ticketreservation.dto.BookedSeatResponseDto;
import com.ticket.ticketreservation.exception.StatusCode;
import com.ticket.ticketreservation.exception.SuccessResponse;
import com.ticket.ticketreservation.service.BookingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
public class BookingController {

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /* 특정 공연의 특정날짜에 예약된 좌석 조회 */
    @GetMapping("performances/seats")
    public ResponseEntity showReservedSeats(@RequestParam(value = "title") @NotBlank  String title,
                                            @RequestParam(value="date") @DateTimeFormat(pattern = "yyyy-MM-dd") @FutureOrPresent @NotNull LocalDate performanceDate){
        List<BookedSeatResponseDto> bookedSeatResponseDtoList = bookingService.showBookedSeats(title, performanceDate);
        return ResponseEntity.ok().body(new SuccessResponse(StatusCode.OK, bookedSeatResponseDtoList));
    }
}
