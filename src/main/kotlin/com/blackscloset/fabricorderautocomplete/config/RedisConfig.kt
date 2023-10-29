package com.blackscloset.fabricorderautocomplete.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {
    @Bean
    fun reactiveRedisTemplate(connectionFactory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, String> {
        val serializationContext = RedisSerializationContext.newSerializationContext<String, String>(StringRedisSerializer.UTF_8).build()
        return ReactiveRedisTemplate(connectionFactory, serializationContext)

    }
}