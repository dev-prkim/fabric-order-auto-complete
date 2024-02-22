package com.blackscloset.fms.handler

import CreateFabricSupplierReq
import com.blackscloset.fms.service.FabricSupplierService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class FabricSupplierHandler(private val fabricSupplierService: FabricSupplierService) {

    suspend fun findAll(request: ServerRequest): ServerResponse {
        val suppliers = fabricSupplierService.findAll()
        return ok().json().bodyValueAndAwait(suppliers)
    }

    suspend fun save(request: ServerRequest): ServerResponse {
        val supplier = request.bodyToMono<CreateFabricSupplierReq>()
        val result = fabricSupplierService.save(supplier.awaitSingle())
        return ok().bodyValueAndAwait(result)
    }
}