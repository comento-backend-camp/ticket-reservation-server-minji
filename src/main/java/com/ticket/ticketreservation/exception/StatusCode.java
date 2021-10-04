package com.ticket.ticketreservation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusCode {

    OK(200, "Ok", "성공"),
    CREATED(201, "Created", "등록 성공"),
    INVALID_INPUT_VALUE(400, "Invalid Input Value", "클라이언트의 요청이 부적절"),
    INVALID_AUTHENTICATION_INFO(401, "Invalid Authentication Info", "인증 실패(등록되지 않은 이메일)"),
    RESOURCE_NOT_FOUND(404, "Resource Not Found", "지정한 리소스를 찾을 수 없음"),
    ACCOUNT_ALREADY_EXISTS(409, "Account Already Exists", "지정한 계정이 이미 존재"),
    RESOURCE_ALREADY_EXISTS(409, "Resource Already Exists", "지정한 좌석이 이미 존재"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error", "서버 에러");

    private final int status;
    private final String message;
    private final String reason;
}
