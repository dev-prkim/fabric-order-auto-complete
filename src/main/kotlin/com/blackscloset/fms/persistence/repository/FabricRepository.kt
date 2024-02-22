package com.blackscloset.fms.persistence.repository

import com.blackscloset.fms.persistence.entity.FabricSupplier
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface FabricRepository: ReactiveMongoRepository<FabricSupplier, String>
