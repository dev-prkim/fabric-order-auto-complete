package helpers

import com.blackscloset.fms.dto.request.CreateFabricSupplierReq
import com.blackscloset.fms.persistence.type.ContactType

object FabricSupplierSample {

    private const val DEFAULT_FABRIC_SUPPLIER_NAME = "test name"
    private const val DEFAULT_FABRIC_SUPPLIER_ADDRESS = "test address"
    private const val DEFAULT_FABRIC_SUPPLIER_TEL = "test tel"
    private const val DEFAULT_FABRIC_SUPPLIER_PHONE = "test phone"
    private const val DEFAULT_FABRIC_SUPPLIER_DESCRIPTION = "test description"
    private val DEFAULT_FABRIC_SUPPLIER_CONTACT_TYPE = ContactType.SMS

    fun sampleCreateFabricSupplierRequest() = CreateFabricSupplierReq(
        name = DEFAULT_FABRIC_SUPPLIER_NAME,
        contactType = DEFAULT_FABRIC_SUPPLIER_CONTACT_TYPE,
        address = DEFAULT_FABRIC_SUPPLIER_ADDRESS,
        tel = DEFAULT_FABRIC_SUPPLIER_TEL,
        phone = DEFAULT_FABRIC_SUPPLIER_PHONE,
        description = DEFAULT_FABRIC_SUPPLIER_DESCRIPTION
    )

}
