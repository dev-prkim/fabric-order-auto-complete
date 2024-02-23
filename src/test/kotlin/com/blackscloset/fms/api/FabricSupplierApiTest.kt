package com.blackscloset.fms.api

import com.blackscloset.fms.persistence.repository.FabricSupplierRepository
import helpers.FabricSupplierApiSupport
import helpers.FabricSupplierSample
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = ["spring.profiles.active=local"])
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
        val createRequest = FabricSupplierSample.sampleCreateFabricSupplierRequest()

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

}