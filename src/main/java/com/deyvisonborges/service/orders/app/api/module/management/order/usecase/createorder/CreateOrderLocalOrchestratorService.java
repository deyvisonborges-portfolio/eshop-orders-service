package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateOrderLocalOrchestratorService {
  private final CreateOrderCommandHandler handler;

  public CreateOrderLocalOrchestratorService(final CreateOrderCommandHandler handler) {
    this.handler = handler;
  }

  @Transactional
  public void execute(final CreateOrderCommand command) {
    try {
      this.handler.handle(command);
    } catch (Exception e) {
      throw new RuntimeException("Fail to save order: " + e.getMessage(), e);
    }
  }
}