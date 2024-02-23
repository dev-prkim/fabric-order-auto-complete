package com.blackscloset.fms.service

import com.blackscloset.fms.dto.request.CreateFabricSupplierReq
import com.blackscloset.fms.dto.response.FabricSupplierListRes
import com.blackscloset.fms.dto.response.FabricSupplierRes
import com.blackscloset.fms.dto.response.toFabricSupplierListRes
import com.blackscloset.fms.dto.response.toFabricSupplierRes
import com.blackscloset.fms.persistence.entity.FabricSupplier
import com.blackscloset.fms.persistence.repository.FabricSupplierRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class FabricSupplierService(private val fabricSupplierRepository: FabricSupplierRepository) {
    suspend fun findFabricSuppliers(): FabricSupplierListRes {
        val inUse: Boolean = true
        return fabricSupplierRepository.findFilteredBy(
            inUse
        ).asFlow()
            .map { it.toFabricSupplierRes() }
            .toList()
            .toFabricSupplierListRes()
    }

    suspend fun create(request: CreateFabricSupplierReq): FabricSupplierRes {
        val id = UUID.randomUUID().toString()
        val savedFabricSupplierEntity: FabricSupplier = fabricSupplierRepository.save(request.toEntity(id)).awaitSingle()
        return savedFabricSupplierEntity.toFabricSupplierRes()
    }

}