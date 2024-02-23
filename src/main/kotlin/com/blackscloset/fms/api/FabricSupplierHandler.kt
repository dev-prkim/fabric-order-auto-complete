package com.blackscloset.fms.api

import com.blackscloset.fms.dto.request.CreateFabricSupplierReq
import com.blackscloset.fms.dto.toFabricSupplierListWrapper
import com.blackscloset.fms.dto.toFabricSupplierWrapper
import com.blackscloset.fms.service.FabricSupplierService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.ok

@Component
class FabricSupplierHandler(private val fabricSupplierService: FabricSupplierService) {

    suspend fun getFabricSuppliers(request: ServerRequest): ServerResponse {
        val suppliers = fabricSupplierService.findFabricSuppliers()
        return ok().bodyValueAndAwait(suppliers.toFabricSupplierListWrapper())
    }

    suspend fun createFabricSupplier(request: ServerRequest): ServerResponse {
        val supplier = request.bodyToMono<CreateFabricSupplierReq>()
        val result = fabricSupplierService.create(supplier.awaitSingle())
        return ok().bodyValueAndAwait(result.toFabricSupplierWrapper())
    }
}