package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderJPAEntity;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderRepository;
import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEvent;
import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventMessage;
import com.deyvisonborges.service.orders.app.messaging.events.order.publishers.CreateOrderEventPublisher;
import com.deyvisonborges.service.orders.core.domain.cqrs.CommandHandler;

import jakarta.transaction.Transactional;

@Service
public class CreateOrderCommandHandler implements CommandHandler<Void, CreateOrderCommand> {

  private final CreateOrderEventPublisher createOrderEvent;
  private final OrderRepository orderRepository;

  public CreateOrderCommandHandler(
    final CreateOrderEventPublisher createOrderEvent,
    final OrderRepository orderRepository
  ) {
    this.createOrderEvent = createOrderEvent;
    this.orderRepository = orderRepository;
  }

  @Override
  @Transactional
  public Void handle(final CreateOrderCommand command) {
    final var orderAggregate = CreateOrderCommand.toAggregate(command);
    this.orderRepository.save(orderAggregate);
    OrderEventMessage eventMessage = OrderEvent.produce(
      orderAggregate.getId().getValue(),
      orderAggregate.getStatus()
    );

    OrderEvent event = OrderEvent.fromOrderEventMessage(eventMessage);

    this.createOrderEvent.applyOn(event);

    return null;
  }
}