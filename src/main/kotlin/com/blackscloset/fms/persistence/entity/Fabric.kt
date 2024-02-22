package com.blackscloset.fms.persistence.entity

import org.springframework.data.annotation.LastModifiedDate
import java.math.BigDecimal
import java.time.Instant

class Fabric(
    val id: String,
    val title: String,
    val cost: BigDecimal = BigDecimal.ZERO,
    val description: String,
    val inUse: Boolean = true,
    val isAvailable: Boolean = true,
    val createdAt: Instant = Instant.now(),
    @LastModifiedDate
    val updatedAt: Instant = Instant.now(),
    val lastOrderAt: Instant
)