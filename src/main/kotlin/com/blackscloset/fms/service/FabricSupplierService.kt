package com.blackscloset.fms.service

import com.blackscloset.fms.dto.request.CreateFabricSupplierReq
import com.blackscloset.fms.dto.response.FabricSupplierListRes
import com.blackscloset.fms.dto.response.FabricSupplierRes
import com.blackscloset.fms.dto.response.toFabricSupplierListRes
import com.blackscloset.fms.dto.response.toFabricSupplierRes
import com.blackscloset.fms.exception.GlobalErrorCode
import com.blackscloset.fms.exception.GlobalException
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
        return fabricSupplierRepository.findAll().asFlow()
            .map { it.toFabricSupplierRes() }
            .toList()
            .toFabricSupplierListRes()
    }

    suspend fun create(request: CreateFabricSupplierReq): FabricSupplierRes {
        validDuplicationSupplierName(request.name)
        validDuplicationSupplierPhone(request.phone)

        val id = UUID.randomUUID().toString()
        val savedFabricSupplierEntity: FabricSupplier = fabricSupplierRepository.save(request.toEntity(id + "")).awaitSingle()
        return savedFabricSupplierEntity.toFabricSupplierRes()
    }

    private suspend fun validDuplicationSupplierName(name: String) {
        // TODO duplication check
        throw GlobalException(GlobalErrorCode.DUPLICATE_NAME, "${name} 은/는 이미 사용중인 이름 입니다.")
    }

    private suspend fun validDuplicationSupplierPhone(phone: String) {
        // TODO duplication check
        throw GlobalException(GlobalErrorCode.DUPLICATE_PHONE, "${phone} 은/는 이미 사용중인 핸드폰번호 입니다.")
    }
}