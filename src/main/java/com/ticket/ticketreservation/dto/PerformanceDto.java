package com.ticket.ticketreservation.dto;

import com.ticket.ticketreservation.domain.Performance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceDto {

    private Long PerformanceId;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private String price;
    private String details;

    public Performance toEntity() {
        return Performance.builder()
                .performanceId(PerformanceId)
                .title(title)
                .startDate(startDate)
                .endDate(endDate)
                .price(price)
                .details(details)
                .build();
    }

    public static PerformanceDto from(Performance performance){
        return PerformanceDto.builder()
                .PerformanceId(performance.getPerformanceId())
                .title(performance.getTitle())
                .startDate(performance.getStartDate())
                .endDate(performance.getEndDate())
                .price(performance.getPrice())
                .details(performance.getDetails())
                .build();
    }
}