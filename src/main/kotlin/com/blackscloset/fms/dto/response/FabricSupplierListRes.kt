package com.blackscloset.fms.dto.response

import com.blackscloset.fms.persistence.entity.FabricSupplier

data class FabricSupplierListRes(
    val fabricSuppliers: List<FabricSupplierRes> = emptyList(),
    val fabricSuppliersCount: Int = 0
)

fun List<FabricSupplierRes>.toFabricSupplierListRes() = FabricSupplierListRes(this, this.size)

