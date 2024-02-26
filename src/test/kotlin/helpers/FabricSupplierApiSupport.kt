package helpers

import com.blackscloset.fms.dto.FabricSupplierListWrapper
import com.blackscloset.fms.dto.FabricSupplierWrapper
import com.blackscloset.fms.dto.request.CreateFabricSupplierReq
import com.blackscloset.fms.dto.response.FabricSupplierListRes
import com.blackscloset.fms.dto.response.FabricSupplierRes
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

class FabricSupplierApiSupport(private val client: WebTestClient) {

    fun createFabricSupplier(
        createRequest: CreateFabricSupplierReq
    ): FabricSupplierRes = client.post()
        .uri("/api/v1/supplier")
        .bodyValue(createRequest)
        .exchange()
        .expectBody<FabricSupplierWrapper<FabricSupplierRes>>()
        .returnResult()
        .responseBody!!
        .content

    fun findFabricSuppliers(): FabricSupplierListRes = client.get()
        .uri("/api/v1/supplier")
        .exchange()
        .expectBody<FabricSupplierListWrapper<FabricSupplierListRes>>()
        .returnResult()
        .responseBody!!
        .content
}
