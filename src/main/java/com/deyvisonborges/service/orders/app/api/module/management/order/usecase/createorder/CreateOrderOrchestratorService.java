package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.messaging.client.rabbitmq.RabbitmqEventEmitter;
import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEvent;
import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventConstants;
import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventMessage;

@Service
public class CreateOrderOrchestratorService {
  private final RabbitmqEventEmitter eventEmitter;

  public CreateOrderOrchestratorService(final RabbitmqEventEmitter eventEmitter) {
    this.eventEmitter = eventEmitter;
  }

  public void initiateOrderCreation(OrderEventMessage orderEventMessage) {
    // Emite o evento de criação de pedido
    eventEmitter.emit(
      OrderEventConstants.ORDER_EXCHANGE_NAME, 
      OrderEventConstants.ORDER_CREATED_EVENT_ROUTING_KEY,
      OrderEvent.fromOrderEventMessage(orderEventMessage)
    );
  }

  public void onOrderCreationFailed(OrderEventMessage orderEventMessage) {
    // Emite um evento de compensação para desfazer a operação de criação na base de escrita
    eventEmitter.emit(
      OrderEventConstants.ORDER_EXCHANGE_NAME, 
      OrderEventConstants.ORDER_COMPENSATION_ROUTING_KEY,
      OrderEvent.fromOrderEventMessage(orderEventMessage)
    );
  }
}
