package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.repositories;

import org.springframework.data.repository.CrudRepository;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.entities.OrderRedisEntity;

public interface OrderReadableRepository extends CrudRepository<OrderRedisEntity, String> {}
