package com.blackscloset.fms.exception

data class GlobalErrorResponse (
    var errorCode: String,
    var errorMessage: String,
    var details: String
)