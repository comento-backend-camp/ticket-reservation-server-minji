package com.ticket.ticketreservation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERFORMANCE_ID")
    private Long performanceId;

    @NotNull
    @Column(name="TITLE")
    private String title;
    @NotNull
    @Column(name="START_DATE")
    private LocalDate startDate;
    @NotNull
    @Column(name="END_DATE")
    private LocalDate endDate;
    @NotNull
    @Column(name="PRICE")
    private String price;
    @NotNull
    @Column(name="DETAILS")
    private String details;
}