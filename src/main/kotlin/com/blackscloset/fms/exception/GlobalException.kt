package com.blackscloset.fms.exception

class GlobalException(val globalErrorCode: GlobalErrorCode, val details: String) : RuntimeException()
