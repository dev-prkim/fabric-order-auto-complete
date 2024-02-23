package com.blackscloset.fms.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class FabricSupplierWrapper<T>(@JsonProperty("fabricSupplier") val content: T)
data class FabricSupplierListWrapper<T>(@JsonProperty("data") val content: T)

fun <T> T.toFabricSupplierWrapper() = FabricSupplierWrapper(this)
fun <T> T.toFabricSupplierListWrapper() = FabricSupplierListWrapper(this)
