package com.ticket.ticketreservation.domain;

import com.ticket.ticketreservation.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOKING_ID")
    private Long bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name="PERFORMANCE_ID")
    private Performance performance;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @NotNull
    @Column(name="SEAT_TYPE")
    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @NotNull
    @Column(name="SEAT_NUMBER")
    private int seatNumber;

    @NotNull
    @FutureOrPresent
    @Column(name="PERFORMANCE_DATE")
    private LocalDate performanceDate;

    @NotNull
    @Column(name="PRICE")
    private int price;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATE_AT")
    private Date createAt;
}