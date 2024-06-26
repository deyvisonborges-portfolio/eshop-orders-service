package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.entities.OrderMongoEntity;

public interface OrderMongoRepository extends MongoRepository<OrderMongoEntity, String> {}
