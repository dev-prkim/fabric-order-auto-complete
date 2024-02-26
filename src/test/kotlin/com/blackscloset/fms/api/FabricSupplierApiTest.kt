package com.blackscloset.fms.api

import com.blackscloset.fms.dto.response.FabricSupplierListRes
import com.blackscloset.fms.persistence.repository.FabricSupplierRepository
import helpers.FabricSupplierApiSupport
import helpers.FabricSupplierSamples
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = ["spring.profiles.active=test"])
class FabricSupplierApiTest(
    @Autowired val webTestClient: WebTestClient,
    @Autowired val fabricSupplierRepository: FabricSupplierRepository
) {
    val fabricSupplierApi = FabricSupplierApiSupport(webTestClient)

    @BeforeEach
    fun setUp() {
        fabricSupplierRepository.deleteAll().block()
    }

    @Test
    fun `test create fabric supplier`() {
        val createRequest = FabricSupplierSamples.sampleCreateFabricSupplierRequest(1)

        val fabricSupplier = fabricSupplierApi.createFabricSupplier(createRequest)

        assertThat(fabricSupplier.name).isEqualTo(createRequest.name)
        assertThat(fabricSupplier.contactType).isEqualTo(createRequest.contactType)
        assertThat(fabricSupplier.address).isEqualTo(createRequest.address)
        assertThat(fabricSupplier.tel).isEqualTo(createRequest.tel)
        assertThat(fabricSupplier.phone).isEqualTo(createRequest.phone)
        assertThat(fabricSupplier.description).isEqualTo(createRequest.description)

        val savedFabricSupplier = fabricSupplierRepository.findAll().collectList().block() ?: emptyList()
        assertThat(savedFabricSupplier.map { m -> m.name })
            .hasSize(1)
            .contains(fabricSupplier.name)
    }
    @Test
    fun `find using fabric suppliers`() {
        val createReq1 = FabricSupplierSamples.sampleCreateFabricSupplierRequest(1)
        val createReq2 = FabricSupplierSamples.sampleCreateFabricSupplierRequest(2)

        fabricSupplierApi.createFabricSupplier(createReq1)
        fabricSupplierApi.createFabricSupplier(createReq2)

        val result1: FabricSupplierListRes = fabricSupplierApi.findFabricSuppliers()

        assertThat(result1.fabricSuppliersCount).isEqualTo(2)

    }


}