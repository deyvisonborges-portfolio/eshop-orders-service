package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.OrderRedisEntity;

@Repository
public interface OrderReadableRepository extends CrudRepository<OrderRedisEntity, String> {}
