package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEvent;
import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEventConstants;
import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEventMessage;
import com.deyvisonborges.service.orders.app.messaging.client.rabbitmq.RabbitmqEventEmitter;

@Service
public class CreateOrderLocalOrchestratorService {
  private final CreateOrderCommandHandler handler;
  private final RabbitmqEventEmitter rabbitmqEventEmitter;

  public CreateOrderLocalOrchestratorService(
    final CreateOrderCommandHandler handler,
    final RabbitmqEventEmitter rabbitmqEventEmitter
  ) {
    this.handler = handler;
    this.rabbitmqEventEmitter = rabbitmqEventEmitter;
  }

  @Transactional
  public void execute(final CreateOrderCommand command) {
    final var order = this.handler.handle(command);

    try {
      TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
        @Override
        public void afterCommit() {
          OrderEventMessage eventMessage = OrderEvent.produce(
            order.getId().getValue(),
            order.getStatus()
          );
          createOrderEvent(eventMessage);
        }
      });
    } catch (Exception e) {
      throw new RuntimeException("Fail to save order: " + e.getMessage(), e);
    }
  }

  public void createOrderEvent(OrderEventMessage orderEventMessage) {
    rabbitmqEventEmitter.emit(
      OrderEventConstants.ORDER_EXCHANGE_NAME, 
      OrderEventConstants.ORDER_CREATION_EVENT_ROUTING_KEY,
      OrderEvent.fromOrderEventMessage(orderEventMessage)
    );
  }
}