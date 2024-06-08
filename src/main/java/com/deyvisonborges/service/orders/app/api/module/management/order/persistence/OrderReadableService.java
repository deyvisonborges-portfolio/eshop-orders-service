package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.util.List;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.entities.OrderRedisEntity;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.repositories.OrderReadableRepository;

@Service
public class OrderReadableService {
  private final OrderReadableRepository repository;

  public OrderReadableService(final OrderReadableRepository repository) {
    this.repository = repository;
  }

  public void save() {
    final var order = new OrderRedisEntity();
    this.repository.save(order);
  }

  public List<OrderRedisEntity> findAll() {
    return (List<OrderRedisEntity>) this.repository.findAll();
  }
}
