package com.blackscloset.fms.exception

import org.springframework.http.HttpStatus

enum class GlobalErrorCode(status: HttpStatus, errorCode: String, errorMessage: String) {
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "E1000", "잘못된 요청입니다."),

    DUPLICATE_NAME(HttpStatus.BAD_REQUEST, "E2000", "중복된 이름 입니다."),
    DUPLICATE_PHONE(HttpStatus.BAD_REQUEST, "E2001", "중복된 핸드폰 번호 입니다."),

    UNCHECKED_ERR(HttpStatus.INTERNAL_SERVER_ERROR, "E9999", "예외가 발생하였습니다.");

    public val status: HttpStatus = status
    public val errorCode: String = errorCode
    public val errorMessage: String = errorMessage
}