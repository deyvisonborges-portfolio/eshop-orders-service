package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEvent;
import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventMessage;
import com.deyvisonborges.service.orders.app.messaging.events.order.publishers.CreateOrderEventPublisher;
import com.deyvisonborges.service.orders.core.domain.cqrs.CommandHandler;

@Service
public class CreateOrderCommandHandler implements CommandHandler<Void, CreateOrderCommand> {

  private final CreateOrderEventPublisher createOrderEvent;
  // private final OrderRepository orderRepository;

  public CreateOrderCommandHandler(
      final CreateOrderEventPublisher createOrderEvent
  // final OrderRepository orderRepository
  ) {
    this.createOrderEvent = createOrderEvent;
    // this.orderRepository = orderRepository;
  }

  @Override
  public Void handle(final CreateOrderCommand command) {
    final var order = CreateOrderCommand.mapper(command);

    // this.orderRepository.save(order);

    OrderEventMessage eventMessage = OrderEvent.produce(
        order.getId().getValue(),
        order.getStatus());

    OrderEvent event = OrderEvent.fromOrderEventMessage(eventMessage);

    this.createOrderEvent.applyOn(event);

    return null;
  }
}