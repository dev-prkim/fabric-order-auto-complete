package com.blackscloset.fabricorderautocomplete.controller

import com.blackscloset.fabricorderautocomplete.model.FabricSupplier
import com.blackscloset.fabricorderautocomplete.service.SupplierService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

/**
 * 원단 공급 업체 관리 컨트롤러
 */
@RestController
@RequestMapping("/api/v1")
class SupplierController(private val supplierService: SupplierService) {

    @GetMapping("/suppliers")
    fun getSuppliers(): Mono<List<String>> {
        return supplierService.getAll()
    }

    @PostMapping("/supplier")
    fun createSupplier(@RequestBody supplier: FabricSupplier): String {
        supplierService.saveSupplier(supplier)
        return "Success"
    }
}