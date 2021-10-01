package com.ticket.ticketreservation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    private Date startDate;
    @NotNull
    @Column(name="END_DATE")
    private Date endDate;
    @NotNull
    @Column(name="PRICE")
    private String price;
    @NotNull
    @Column(name="DETAILS")
    private String details;
}