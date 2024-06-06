package com.deyvisonborges.service.orders.app.persistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EntityScan(basePackages = "com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.entities")
@EnableRedisRepositories(basePackages = "com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.repositories")
public class RedisConfig {
  @Bean
  RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    final RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    return template;
  }
}
