package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.deleteorder;

import java.util.UUID;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderRepository;
import com.deyvisonborges.service.orders.core.domain.cqrs.CommandHandler;

public class DeleteOrderCommandHandler implements CommandHandler<Void, String> {
  private final OrderRepository repository;

  public DeleteOrderCommandHandler(final OrderRepository repository) {
    this.repository = repository;
  }

  @Override
  public Void handle(String orderId) {
    try {
      UUID.fromString(orderId);  
    } catch (Exception e) {
      throw new RuntimeException("Invalid UUID");
    }
    this.repository.deleteById(orderId);
    return null;
  }
}
