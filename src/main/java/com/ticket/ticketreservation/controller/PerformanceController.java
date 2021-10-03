package com.ticket.ticketreservation.controller;

import com.ticket.ticketreservation.domain.Performance;
import com.ticket.ticketreservation.exception.StatusCode;
import com.ticket.ticketreservation.exception.SuccessResponse;
import com.ticket.ticketreservation.service.PerformanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api")
public class PerformanceController {

    private final PerformanceService performanceService;
    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    /* 현재 날짜에서 예약가능한 특정 공연들 정보 조회(날짜 오름차순) */
    @GetMapping("/performances")
    public ResponseEntity showPerformanceInfo(@RequestParam(value = "title") @NotBlank String title){
        LocalDate today = LocalDate.now();
        List<Performance> performanceList = performanceService.showPerformanceList(title, today);
        return ResponseEntity.ok().body(new SuccessResponse(StatusCode.OK, performanceList));
    }
}