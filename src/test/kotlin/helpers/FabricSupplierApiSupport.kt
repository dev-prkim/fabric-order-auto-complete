package helpers

import com.blackscloset.fms.dto.FabricSupplierWrapper
import com.blackscloset.fms.dto.request.CreateFabricSupplierReq
import com.blackscloset.fms.dto.response.FabricSupplierRes
import com.blackscloset.fms.dto.toFabricSupplierWrapper
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody

class FabricSupplierApiSupport(private val client: WebTestClient) {

    fun createFabricSupplier(
        createRequest: CreateFabricSupplierReq = FabricSupplierSample.sampleCreateFabricSupplierRequest()
    ): FabricSupplierRes = client.post()
        .uri("/api/v1/supplier")
        .bodyValue(createRequest)
        .exchange()
        .expectBody<FabricSupplierWrapper<FabricSupplierRes>>()
        .returnResult()
        .responseBody!!
        .content
}
