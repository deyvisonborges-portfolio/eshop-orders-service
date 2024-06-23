package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEvent;
import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEventMessage;
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
    try {
      this.orderRepository.save(orderAggregate);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
          @Override
          public void afterCommit() {
            OrderEventMessage eventMessage = OrderEvent.produce(
              orderAggregate.getId().getValue(),
              orderAggregate.getStatus()
            );
            createOrderOrchestratorService.createOrderEvent(eventMessage);
          }
        });
        
    } catch (Exception e) {
      throw new RuntimeException("Falha ao salvar o pedido: " + e.getMessage(), e);
    }
    return null;
  }
}
