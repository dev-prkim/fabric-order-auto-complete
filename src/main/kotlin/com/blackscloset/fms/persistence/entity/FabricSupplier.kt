package com.blackscloset.fms.persistence.entity

import com.blackscloset.fms.dto.response.FabricSupplierRes
import com.blackscloset.fms.dto.toFabricSupplierWrapper
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
    val createdAt: Instant = Instant.now(),
    @LastModifiedDate
    val updatedAt: Instant = Instant.now(),
    @Field("fabrics") val fabrics: List<Fabric> = listOf()
)