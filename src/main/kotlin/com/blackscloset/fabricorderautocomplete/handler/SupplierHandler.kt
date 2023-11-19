package com.blackscloset.fabricorderautocomplete.handler

import com.blackscloset.fabricorderautocomplete.model.FabricSupplier
import com.blackscloset.fabricorderautocomplete.service.SupplierService
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class SupplierHandler(private val supplierService: SupplierService) {

    suspend fun findAll(request: ServerRequest): ServerResponse {
        val suppliers = supplierService.findAll()
        return ok().json().bodyValueAndAwait(suppliers.awaitSingle())
    }

    suspend fun save(request: ServerRequest): ServerResponse {
        val supplier = request.bodyToMono<FabricSupplier>()
        val saveResult = supplierService.save(supplier).awaitSingleOrNull()
        return ok().bodyValueAndAwait(saveResult ?: "Ok")

    }
}