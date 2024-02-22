package com.blackscloset.fabricorderautocomplete.service

import com.blackscloset.fabricorderautocomplete.model.FabricSupplier
import com.blackscloset.fabricorderautocomplete.repository.SupplierRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class SupplierService(private val supplierRepository: SupplierRepository) {
    fun getAll(): Mono<List<String>> {
        return supplierRepository.findAll()
    }

    fun saveSupplier(supplier: FabricSupplier) {
        supplierRepository.save(supplier)
    }
}