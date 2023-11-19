package com.blackscloset.fabricorderautocomplete.router

import com.blackscloset.fabricorderautocomplete.handler.SupplierHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class SupplierRouter(private val supplierHandler: SupplierHandler) {

    @Bean
    fun router() =
        coRouter {
            "/api/v1/supplier".nest {
                listOf(
                    GET("", supplierHandler::findAll),
                    POST("", supplierHandler::save)
                )
            }
        }
}