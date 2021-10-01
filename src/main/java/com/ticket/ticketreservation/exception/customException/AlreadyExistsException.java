package com.ticket.ticketreservation.exception.customException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// 이미 존재하는 리소스이기 때문에 중복 생성이 불가능한 경우
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Already Exists")
public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String message) {
        super(message);
    }
}
