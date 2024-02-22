package com.blackscloset.fms.service

import CreateFabricSupplierReq
import FabricSupplierListRes
import FabricSupplierRes
import com.blackscloset.fms.persistence.entity.FabricSupplier
import com.blackscloset.fms.persistence.repository.FabricSupplierRepository
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service
import toFabricSupplierListRes
import java.util.UUID

@Service
class FabricSupplierService(private val fabricSupplierRepository: FabricSupplierRepository) {
    suspend fun findAll(): FabricSupplierListRes = fabricSupplierRepository.findAll()
        .collectList()
        .awaitSingle()
        .toFabricSupplierListRes()

    suspend fun save(request: CreateFabricSupplierReq): FabricSupplierRes {
        val id = UUID.randomUUID().toString()
        val fabricSupplier: FabricSupplier = fabricSupplierRepository.save(request.toEntity(id)).awaitSingle()
        return fabricSupplier.toFabricSupplierRes()
    }

}