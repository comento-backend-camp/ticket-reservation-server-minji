package com.ticket.ticketreservation.exception.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 지정한 리소스를 찾을 수 없음(데이터 없음)
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource Not Found")
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
