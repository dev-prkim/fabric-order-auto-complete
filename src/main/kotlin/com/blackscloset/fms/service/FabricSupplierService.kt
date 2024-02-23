package com.blackscloset.fms.service

import com.blackscloset.fms.dto.FabricSupplierWrapper
import com.blackscloset.fms.dto.request.CreateFabricSupplierReq
import com.blackscloset.fms.dto.response.FabricSupplierListRes
import com.blackscloset.fms.dto.response.FabricSupplierRes
import com.blackscloset.fms.dto.response.toFabricSupplierListRes
import com.blackscloset.fms.dto.response.toFabricSupplierRes
import com.blackscloset.fms.persistence.entity.FabricSupplier
import com.blackscloset.fms.persistence.repository.FabricSupplierRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import com.blackscloset.fms.dto.toFabricSupplierWrapper
import java.util.UUID

@Service
class FabricSupplierService(private val fabricSupplierRepository: FabricSupplierRepository) {
    suspend fun getAll(): FabricSupplierListRes = fabricSupplierRepository.findAll()
        .collectList()
        .awaitSingle()
        .toFabricSupplierListRes()

    suspend fun create(request: CreateFabricSupplierReq): FabricSupplierRes {
        val id = UUID.randomUUID().toString()
        val savedFabricSupplierEntity: FabricSupplier = fabricSupplierRepository.save(request.toEntity(id)).awaitSingle()
        return savedFabricSupplierEntity.toFabricSupplierRes()
    }

}