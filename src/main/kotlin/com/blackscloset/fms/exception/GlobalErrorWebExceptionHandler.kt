package com.blackscloset.fms.exception

import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
@Order(-2)
class GlobalExceptionHandler(
    errorAttributes: ErrorAttributes,
    resources: WebProperties.Resources,
    applicationContext: ApplicationContext,
    serverCodecConfigurer: ServerCodecConfigurer,
) : AbstractErrorWebExceptionHandler(
    errorAttributes, resources, applicationContext
) {
    init {
        super.setMessageReaders(serverCodecConfigurer.readers)
        super.setMessageWriters(serverCodecConfigurer.writers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes?): RouterFunction<ServerResponse> =
        RouterFunctions.route(RequestPredicates.all(), this::handleError)

    fun handleError(request: ServerRequest): Mono<ServerResponse> {
        return when (val throwable = super.getError(request)) {
            is GlobalException -> {
                ServerResponse.status(throwable.globalErrorCode.status)
                    .bodyValue(
                        GlobalErrorResponse(
                            errorCode = throwable.globalErrorCode.errorCode,
                            errorMessage = throwable.globalErrorCode.errorMessage,
                            details = throwable.details
                        )
                    )
            }
            else -> {
                ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .bodyValue(
                        GlobalErrorResponse(
                            errorCode = GlobalErrorCode.UNCHECKED_ERR.errorCode,
                            errorMessage = GlobalErrorCode.UNCHECKED_ERR.errorCode,
                            details = ""
                        )
                    )
            }
        }
    }
}