package com.blackscloset.fms.exception

import org.springframework.http.HttpStatus

class GlobalException(globalErrorCode: GlobalErrorCode, details: String) : RuntimeException() {
    public val globalErrorCode: GlobalErrorCode = globalErrorCode
    public val details: String = details
}
