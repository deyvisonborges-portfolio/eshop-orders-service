package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.entities.OrderRedisEntity;

public interface OrderRedisRepository extends CrudRepository<OrderRedisEntity, String> {
  Page<OrderRedisEntity> findAll(Specification<OrderRedisEntity> whereClause, Pageable page);
}