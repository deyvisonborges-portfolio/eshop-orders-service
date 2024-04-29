package com.deyvisonborges.service.orders.core.modules.management.order.usecase.createorder;

import com.deyvisonborges.service.orders.core.domain.cqrs.CommandHandler;

public class CreateOrderCommandHandler implements CommandHandler<Void, CreateOrderCommand> {

  @Override
  public Void handle(CreateOrderCommand command) {
    throw new UnsupportedOperationException("Unimplemented method 'handle'");
  }
}