package com.ticket.ticketreservation.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

    public static ErrorResponse of(StatusCode code) {
        return new ErrorResponse(code);
    }
}
