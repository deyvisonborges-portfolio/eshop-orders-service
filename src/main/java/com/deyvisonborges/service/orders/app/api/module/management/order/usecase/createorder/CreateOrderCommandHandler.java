package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.core.domain.cqrs.CommandHandler;

@Service
public class CreateOrderCommandHandler implements CommandHandler<Void, CreateOrderCommand> {

  @Override
  public Void handle(CreateOrderCommand command) {
    throw new UnsupportedOperationException("Unimplemented method 'handle'");
  }
}