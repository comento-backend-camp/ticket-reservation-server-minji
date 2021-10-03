package com.ticket.ticketreservation.domain;

import com.ticket.ticketreservation.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SEAT_ID")
    private Long seatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name="PERFORMANCE_ID")
    private Performance performance;

    @NotNull
    @Column(name="SEAT_TYPE")
    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @NotNull
    @Column(name="SEAT_NUMBER")
    private int seatNumber;

    @NotNull
    @Column(name="PRICE")
    private int price;

    @NotNull
    @FutureOrPresent
    @Column(name="PERFORMANCE_DATE")
    private LocalDate performanceDate;
}