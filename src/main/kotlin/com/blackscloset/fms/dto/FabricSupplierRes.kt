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