package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.api.module.management.order.events.order.OrderEvent;
import com.deyvisonborges.service.orders.app.api.module.management.order.events.order.OrderEventMessage;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderWritableRepository;
import com.deyvisonborges.service.orders.core.domain.cqrs.CommandHandler;

import jakarta.transaction.Transactional;

@Service
public class CreateOrderCommandHandler implements CommandHandler<Void, CreateOrderCommand> {
  private final OrderWritableRepository orderRepository;
  private final CreateOrderOrchestratorService createOrderOrchestratorService;

  public CreateOrderCommandHandler(
    final OrderWritableRepository orderRepository,
    final CreateOrderOrchestratorService createOrderOrchestratorService
  ) {
    this.orderRepository = orderRepository;
    this.createOrderOrchestratorService = createOrderOrchestratorService;
  }

  @Override
  @Transactional
  public Void handle(final CreateOrderCommand command) {
    final var createOrderCommandValidation = new CreateOrderValidation();
    createOrderCommandValidation.validate(command);

    final var orderAggregate = CreateOrderCommand.toAggregate(command);
    this.orderRepository.save(orderAggregate);
    
    OrderEventMessage eventMessage = OrderEvent.produce(
      orderAggregate.getId().getValue(),
      orderAggregate.getStatus()
    );

    this.createOrderOrchestratorService.initiateOrderCreation(eventMessage);
    return null;
  }
}