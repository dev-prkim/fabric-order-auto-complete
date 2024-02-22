import com.blackscloset.fms.persistence.entity.FabricSupplier

data class FabricSupplierListRes(
    val fabricSuppliers: List<FabricSupplierRes> = emptyList()
)

fun List<FabricSupplier>.toFabricSupplierListRes() = FabricSupplierListRes(this.map { fabricSupplier ->
    FabricSupplierRes(
        fabricSupplier.id,
        fabricSupplier.name,
        fabricSupplier.contactType,
        fabricSupplier.address,
        fabricSupplier.tel,
        fabricSupplier.phone,
        fabricSupplier.description,
        fabricSupplier.inUse,
        fabricSupplier.createdAt
    )
})