package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.deleteorder;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderWritableRepository;
import com.deyvisonborges.service.orders.core.domain.cqrs.CommandHandler;

@Service
public class DeleteOrderCommandHandler implements CommandHandler<Void, String> {
  private final OrderWritableRepository repository;

  public DeleteOrderCommandHandler(final OrderWritableRepository repository) {
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
