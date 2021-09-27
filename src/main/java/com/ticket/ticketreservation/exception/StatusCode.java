package com.ticket.ticketreservation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum StatusCode {


        OK(200, HttpStatus.OK, "Ok", "성공"),
    CREATED(201, HttpStatus.CREATED, "Created", "이메일 등록 성공"),
    INVALID_INPUT_VALUE(400, HttpStatus.BAD_REQUEST, "Invalid Input Value", "클라이언트의 요청이 부적절"),
    INVALID_AUTHENTICATION_INFO(401, HttpStatus.UNAUTHORIZED, "Invalid Authentication Info", "인증 실패(등록되지 않은 이메일)"),
    RESOURCE_NOT_FOUND(404, HttpStatus.NOT_FOUND, "Resource Not Found", "지정한 리소스를 찾을 수 없음"),
    ACCOUNT_ALREADY_EXISTS(409, HttpStatus.CONFLICT, "Account Already Exists", "지정한 계정이 이미 존재"),
    RESOURCE_ALREADY_EXISTS(409, HttpStatus.CONFLICT, "Resource Already Exists", "지정한 리소스 이미 존재"),
    INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", "서버 에러");


    private final int status;
    private final HttpStatus httpStatus;
    private final String message;
    private final String reason;

}
