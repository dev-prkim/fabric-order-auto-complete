package com.blackscloset.fabricorderautocomplete.repository

import com.blackscloset.fabricorderautocomplete.model.FabricSupplier
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import java.util.UUID

@Repository
class SupplierRepository(private val reactiveRedisTemplate: ReactiveRedisTemplate<String, String>) {
    private val redisKeyPrefix = "fabric-supplier"

    fun findAll(): Mono<List<String>> {
        return reactiveRedisTemplate.keys("$redisKeyPrefix*")
            .collectList()
            .flatMap { keys -> reactiveRedisTemplate.opsForValue().multiGet(keys) }
    }

    fun save(fabricSupplier: Mono<FabricSupplier>): Mono<Void> {
        // 고유키 생성
        val key = "$redisKeyPrefix:${generateUniqueId()}"

        return fabricSupplier.flatMap { supplier ->
            // FabricSupplier 객체를 JSON 문자열로 직렬화
            val json = Json.encodeToString(supplier)
            reactiveRedisTemplate.opsForValue().set(key, json)
                .then()
        }
    }

    private fun generateUniqueId(): String {
        return UUID.randomUUID().toString()
    }
}