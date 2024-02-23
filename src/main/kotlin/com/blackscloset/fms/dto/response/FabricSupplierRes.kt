package com.blackscloset.fms.dto.response

import com.blackscloset.fms.persistence.entity.FabricSupplier
import com.blackscloset.fms.persistence.type.ContactType
import java.time.Instant

data class FabricSupplierRes(
    val id: String,
    val name: String,
    val contactType: ContactType,
    val address: String?,
    val tel: String?,
    val phone: String,
    val description: String?,
    val inUse: Boolean,
    val createdAt: Instant
)

fun FabricSupplier.toFabricSupplierRes() = FabricSupplierRes(
    id = this.id,
    name = this.name,
    contactType = this.contactType,
    address = this.address,
    tel = this.tel,
    phone = this.phone,
    description = this.description,
    inUse = this.inUse,
    createdAt = this.createdAt
)