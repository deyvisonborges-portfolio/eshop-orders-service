package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderReadableRepository;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderWritableRepository;
import com.deyvisonborges.service.orders.core.domain.cqrs.CommandHandler;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;

import jakarta.transaction.Transactional;

@Service
public class CreateOrderCommandHandler implements CommandHandler<Order, CreateOrderCommand> {
  private final OrderReadableRepository orderReadableRepository;
  private final OrderWritableRepository orderWritableRepository;

  public CreateOrderCommandHandler(
    final OrderWritableRepository orderWritableRepository,
    final OrderReadableRepository orderReadableRepository
  ) {
    this.orderReadableRepository = orderReadableRepository;
    this.orderWritableRepository = orderWritableRepository;
  }

  @Override
  @Transactional
  public Order handle(final CreateOrderCommand command) {
    final var createOrderCommandValidation = new CreateOrderValidation();
    createOrderCommandValidation.validate(command);
    final var order = CreateOrderCommand.toAggregate(command);
    this.orderWritableRepository.save(order);
    this.orderReadableRepository.save(order);
    return order;
  }
}
