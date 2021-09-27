package com.ticket.ticketreservation.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse<T> {
    private int status;
    private String message;
    private Object data;

    public SuccessResponse(StatusCode code) {
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.data = code.getReason();
    }

    public static SuccessResponse of(StatusCode code) {
        return new SuccessResponse(code);
    }
}
