package com.deyvisonborges.service.orders.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.deyvisonborges.service.orders.app.api.module.management.order.persistence.write.repositories")
@EnableRedisRepositories(basePackages = "com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.repositories")
public class SpringDataConfig {}
