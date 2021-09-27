package com.ticket.ticketreservation.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.ticket.ticketreservation.exception.customException.AlreadyExistsException;
import com.ticket.ticketreservation.exception.customException.ResourceNotFoundException;
import com.ticket.ticketreservation.exception.customException.UnauthorizedException;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

/**
 * GlobalExceptionHandler 백엔드 에서 에러가 발생하면 요청하는 쪽으로 에러 상태값을 보내준다
 */

@ControllerAdvice   // 모든 예외 한 곳에서 처리
@Slf4j
public class GlobalExceptionHandler {

    private static StatusCode code;

    /*
    *   javax.validation.Valid or @Validated 으로 binding error 발생
    *   enum type 일치하지 않아 binding 못할 경우 발생
    */
    @ExceptionHandler({MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class, JsonParseException.class, ConstraintViolationException.class,})
    protected ResponseEntity<ErrorResponse> InvaidInputExceptionHandler(Exception e) {
        code = StatusCode.INVALID_INPUT_VALUE;
        ErrorResponse response = ErrorResponse.of(code);
        return new ResponseEntity<>(response, code.getHttpStatus());
    }

    /* 이미 이메일이 존재하거나 해당 좌석이 이미 예약된 좌석일 때 예외 처리 */
    @ExceptionHandler(value = AlreadyExistsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> AlreadyExistsExceptionHandler(AlreadyExistsException e) {
        ErrorResponse response;

        if(e.getMessage() == "MemberService"){ // 이메일 중복
            code = StatusCode.ACCOUNT_ALREADY_EXISTS;
            response = ErrorResponse.of(code);
        } else{ // 좌석 중복
            code = StatusCode.RESOURCE_ALREADY_EXISTS;
            response = ErrorResponse.of(code);
        }
        return new ResponseEntity<>(response, code.getHttpStatus());
    }

    /* 지정한 리소스를 찾을 수 없음 (데이터 없음) */
    @ExceptionHandler({NoSuchElementException.class, NotFoundException.class, ResourceNotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    protected ResponseEntity<ErrorResponse> NotFoundExceptionHandler(Throwable t) {
        code = StatusCode.RESOURCE_NOT_FOUND;
        ErrorResponse response = ErrorResponse.of(code);
        return new ResponseEntity<>(response, code.getHttpStatus());
    }

    /* 인증 실패 */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    protected ResponseEntity<ErrorResponse> UnauthorizedExceptionHandler(UnauthorizedException e) {
        code = StatusCode.INVALID_AUTHENTICATION_INFO;
        ErrorResponse response = ErrorResponse.of(code);
        return new ResponseEntity<>(response, code.getHttpStatus());
    }

    /* 나머지 예외 처리 */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> ExceptionHandler(Exception e) {
        code = StatusCode.INTERNAL_SERVER_ERROR;
        ErrorResponse response = ErrorResponse.of(code);
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, code.getHttpStatus());
    }
}
