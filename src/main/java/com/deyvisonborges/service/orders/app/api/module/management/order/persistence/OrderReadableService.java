package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

// import java.util.Optional;

import org.springframework.stereotype.Service;

// import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.OrderRedisEntity;
// import com.deyvisonborges.service.orders.core.modules.management.order.Order;

@Service
public class OrderReadableService {
  private final OrderReadableRepository repository;

  public OrderReadableService(final OrderReadableRepository repository) {
    this.repository = repository;
  }

  // public Order getOrderById(final String id) {
  //   Optional<OrderRedisEntity> order = repository.findById(id);
  //   if (order.isPresent()) return order.get();
  //   if (order != null) {
  //     OrderRedisEntity redisEntity = new OrderRedisEntity();
  //     repository.save(redisEntity);
  //   }
  //   return order;
  // }
}
