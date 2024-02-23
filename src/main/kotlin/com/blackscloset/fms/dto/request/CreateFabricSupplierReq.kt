package com.blackscloset.fms.dto.request

import com.blackscloset.fms.persistence.entity.FabricSupplier
import com.blackscloset.fms.persistence.type.ContactType
import jakarta.validation.constraints.NotBlank

data class CreateFabricSupplierReq(
    @field:NotBlank
    val name: String,
    val contactType: ContactType? = ContactType.SMS,
    val address: String?,
    val tel: String?,
    @field:NotBlank
    val phone: String,
    val description: String?
) {
    fun toEntity(id: String): FabricSupplier = FabricSupplier(
        id = id,
        name = this.name,
        contactType = this.contactType!!,
        address = this.address,
        tel = this.tel,
        phone = this.phone,
        description = this.description
    )
}