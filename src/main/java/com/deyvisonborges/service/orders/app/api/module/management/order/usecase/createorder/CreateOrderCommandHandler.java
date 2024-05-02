package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderRepository;
import com.deyvisonborges.service.orders.app.messaging.artifacts.events.CreateOrderEvent;
import com.deyvisonborges.service.orders.core.domain.cqrs.CommandHandler;
import com.deyvisonborges.service.orders.core.modules.management.order.event.OrderEvent;

@Service
public class CreateOrderCommandHandler implements CommandHandler<Void, CreateOrderCommand> {

  private final CreateOrderEvent createOrderEvent;
  private final OrderRepository orderRepository;

  public CreateOrderCommandHandler(
    final CreateOrderEvent createOrderEvent,
    final OrderRepository orderRepository
  ) {
    this.createOrderEvent = createOrderEvent;
    this.orderRepository = orderRepository;
  }

  @Override
  public Void handle(final CreateOrderCommand command) {
    final var order = CreateOrderCommand.mapper(command);
    
    this.orderRepository.save(order);
    
    this.createOrderEvent.applyOn(
      OrderEvent.produce(
        order.getId().getValue(), 
        order.getStatus()
      )
    );
    return null;
  }
}