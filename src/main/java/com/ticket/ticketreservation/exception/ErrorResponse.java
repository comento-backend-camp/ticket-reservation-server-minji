package com.ticket.ticketreservation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int status;
    private String message;
    private String reason;

    public ErrorResponse(StatusCode code) {
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.reason = code.getReason();
    }
}
