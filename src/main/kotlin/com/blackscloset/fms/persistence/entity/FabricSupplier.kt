package com.blackscloset.fms.persistence.entity

import FabricSupplierRes
import com.blackscloset.fms.persistence.type.ContactType
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.Instant

@Document
data class FabricSupplier(
    @Id val id: String,
    val name: String,
    val contactType: ContactType = ContactType.SMS,
    val address: String?,
    val tel: String?,
    val phone: String,
    val description: String?,
    val inUse: Boolean = true,
    val createdAt: Instant = Instant.now(),
    @LastModifiedDate
    val updatedAt: Instant = Instant.now(),
    @Field("fabrics") val fabrics: List<Fabric> = listOf()
) {
    fun toFabricSupplierRes(): FabricSupplierRes = FabricSupplierRes(
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
}