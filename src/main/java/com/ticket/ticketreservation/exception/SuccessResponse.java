package com.ticket.ticketreservation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse {

    private int status;
    private String message;
    private Object data;

    public SuccessResponse(StatusCode code, Object data) {
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.data = data;
    }
}
