package com.blackscloset.fms.persistence.repository

import com.blackscloset.fms.persistence.entity.FabricSupplier
import com.blackscloset.fms.persistence.sortBy
import com.blackscloset.fms.persistence.whereProperty
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Query
import reactor.core.publisher.Flux

interface FabricSupplierCustomRepository {
    fun findFilteredBy(inUse: Boolean): Flux<FabricSupplier>
}

class FabricSupplierCustomRepositoryImpl(private val mongoTemplate: ReactiveMongoTemplate) : FabricSupplierCustomRepository {

    override fun findFilteredBy(
        inUse: Boolean
    ): Flux<FabricSupplier> {
        val query = Query()
            .with(sortBy(FabricSupplier::createdAt).descending())

        inUse.let { query.addCriteria(inUseEquals(it)) }

        return mongoTemplate.find(query)
    }

    private fun inUseEquals(it: Boolean) = whereProperty(FabricSupplier::inUse).`is`(it)

}