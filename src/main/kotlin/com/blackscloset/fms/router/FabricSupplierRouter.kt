package com.blackscloset.fms.router

import com.blackscloset.fms.api.FabricSupplierHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class FabricSupplierRouter(private val fabricSupplierHandler: FabricSupplierHandler) {

    @Bean
    fun router() =
        coRouter {
            "/api/v1/supplier".nest {
                listOf(
                    GET("", fabricSupplierHandler::getFabricSuppliers),
                    POST("", fabricSupplierHandler::createFabricSupplier)
                )
            }
        }
}