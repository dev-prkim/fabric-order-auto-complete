package com.blackscloset.fms.persistence.repository

import com.blackscloset.fms.persistence.entity.FabricSupplier
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.query.Query
import reactor.core.publisher.Flux

interface FabricSupplierCustomRepository {
//    fun findFilteredBy(name: String): Flux<FabricSupplier>
}

class FabricSupplierCustomRepositoryImpl(private val mongoTemplate: ReactiveMongoTemplate) : FabricSupplierCustomRepository {

//    override fun findFilteredBy(
//        name: String
//    ): Flux<FabricSupplier> {
//        val query = Query()
//            .with(sortBy(FabricSupplier::createdAt).descending())
//
//        name.let { query.addCriteria(nameEquals(it)) }
//
//        return mongoTemplate.find(query)
//    }
//
//    private fun nameEquals(it: String) = whereProperty(FabricSupplier::name).`is`(it)

}