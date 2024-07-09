// package com.deyvisonborges.service.orders.app.config;

// import org.springframework.cache.annotation.EnableCaching;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.cache.RedisCacheConfiguration;
// import org.springframework.data.redis.cache.RedisCacheManager;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.serializer.RedisSerializationContext;

// import java.util.Objects;

// @Configuration
// @EnableCaching
// public class CacheConfig {

//   private final RedisTemplate<String, Object> redisTemplate;

//   public CacheConfig(RedisTemplate<String, Object> redisTemplate) {
//     this.redisTemplate = redisTemplate;
//   }

//   @Bean
//   public RedisCacheManager cacheManager() {
//     return RedisCacheManager.builder(Objects.requireNonNull(redisTemplate.getConnectionFactory()))
//       .cacheDefaults(redisCacheConfiguration())
//       .build();
//   }

//   private RedisCacheConfiguration redisCacheConfiguration() {
//     return RedisCacheConfiguration.defaultCacheConfig()
//       .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()));
//   }
// }
