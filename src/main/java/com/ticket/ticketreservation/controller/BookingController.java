package com.ticket.ticketreservation.controller;

import com.ticket.ticketreservation.dto.BookedSeatResponseDto;
import com.ticket.ticketreservation.dto.BookingRequestDto;
import com.ticket.ticketreservation.dto.BookingResponseDto;
import com.ticket.ticketreservation.exception.StatusCode;
import com.ticket.ticketreservation.exception.SuccessResponse;
import com.ticket.ticketreservation.service.BookingHistoryService;
import com.ticket.ticketreservation.service.BookingService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/performances")
public class BookingController {

    private final BookingService bookingService;
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /* 특정 공연의 특정날짜에 예약된 좌석 조회 */
    @GetMapping("/seats")
    public ResponseEntity showBookedSeats(@RequestParam(value = "title") @NotBlank  String title,
                                            @RequestParam(value="date") @DateTimeFormat(pattern = "yyyy-MM-dd") @FutureOrPresent @NotNull LocalDate performanceDate){
        List<BookedSeatResponseDto> bookedSeatResponseDtoList = bookingService.showBookedSeats(title, performanceDate);
        return ResponseEntity.ok().body(new SuccessResponse(StatusCode.OK, bookedSeatResponseDtoList));
    }

    /* 공연 예약 정보 조회 */
    @GetMapping("/booking/{memberEmail}")
    public ResponseEntity showBookingInfo(@PathVariable(value = "memberEmail") @Email @NotBlank String memberEmail){
        List<BookingResponseDto> bookingDtoList = bookingService.showBookingInfo(memberEmail);
        return ResponseEntity.ok().body(new SuccessResponse(StatusCode.OK, bookingDtoList));
    }

    /* 공연 예약 */
    @PostMapping("/booking")
    public ResponseEntity booking(@RequestBody @Valid BookingRequestDto bookingRequestDto, Errors errors){
        if(errors.hasErrors()){
            bookingService.saveFailLog(bookingRequestDto);
        }
        BookingResponseDto bookingResponseDto = bookingService.saveBooking(bookingRequestDto);
        bookingService.saveSuccessLog(bookingRequestDto);
        return ResponseEntity.created(URI.create("/booking/" + bookingRequestDto.getMemberEmail())).body(new SuccessResponse(StatusCode.CREATED, bookingResponseDto));
    }
}
