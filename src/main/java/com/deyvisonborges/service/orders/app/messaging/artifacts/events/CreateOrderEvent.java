package com.deyvisonborges.service.orders.app.messaging.artifacts.events;

import org.springframework.stereotype.Component;

import com.deyvisonborges.service.orders.app.messaging.client.rabbitmq.RabbitmqEventEmitter;
import com.deyvisonborges.service.orders.core.domain.EventEmitter;
import com.deyvisonborges.service.orders.core.modules.management.order.event.OrderEvent;
import com.deyvisonborges.service.orders.core.modules.management.order.event.OrderEventConstants;

@Component
public class CreateOrderEvent extends EventEmitter<OrderEvent> {
  private final RabbitmqEventEmitter emitter;

  public CreateOrderEvent(final RabbitmqEventEmitter emitter) {
    this.emitter = emitter;
  }

  @Override
  public void applyOn(final OrderEvent command) {
    this.emitter.emit(
      OrderEventConstants.ORDER_EXCHANGE_NAME,
      OrderEventConstants.ORDER_CREATED_EVENT_ROUTING_KEY,
      command
    );
  }
}
