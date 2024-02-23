package com.blackscloset.fms.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class FabricSupplierWrapper<T>(@JsonProperty("fabricSupplier") val content: T)

fun <T> T.toFabricSupplierWrapper() = FabricSupplierWrapper(this)
