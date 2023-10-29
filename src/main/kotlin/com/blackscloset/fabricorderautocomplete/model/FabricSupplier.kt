package com.blackscloset.fabricorderautocomplete.model

import com.blackscloset.fabricorderautocomplete.model.type.ContactType
import kotlinx.serialization.Serializable

@Serializable
data class FabricSupplier(
    val id: Long,
    val name: String,
    val contactType: ContactType,
    val address: String,
    val inUse: Boolean
)
