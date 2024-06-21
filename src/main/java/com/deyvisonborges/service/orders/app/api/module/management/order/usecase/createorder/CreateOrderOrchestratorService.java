package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEvent;
import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEventConstants;
import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEventMessage;
import com.deyvisonborges.service.orders.app.messaging.client.rabbitmq.RabbitmqEventEmitter;

@Service
public class CreateOrderOrchestratorService {
  private final RabbitmqEventEmitter eventEmitter;

  public CreateOrderOrchestratorService(final RabbitmqEventEmitter eventEmitter) {
    this.eventEmitter = eventEmitter;
  }

  public void createOrderEvent(OrderEventMessage orderEventMessage) {
    eventEmitter.emit(
      OrderEventConstants.ORDER_EXCHANGE_NAME, 
      OrderEventConstants.ORDER_CREATED_EVENT_ROUTING_KEY,
      OrderEvent.fromOrderEventMessage(orderEventMessage)
    );
  }

  public void onOrderCreationFailed(OrderEventMessage orderEventMessage) {
    eventEmitter.emit(
      OrderEventConstants.ORDER_EXCHANGE_NAME, 
      OrderEventConstants.ORDER_COMPENSATION_ROUTING_KEY,
      OrderEvent.fromOrderEventMessage(orderEventMessage)
    );
  }
}
