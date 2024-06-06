package com.deyvisonborges.service.orders.app.messaging.events.order.listeners;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderReadableService;
import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventConstants;
import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventMessage;

@Component
@Transactional
public class UpsertOrderEventListener {
  private final OrderReadableService orderReadableService;

  public UpsertOrderEventListener(final OrderReadableService orderReadableService) {
    this.orderReadableService = orderReadableService;
  }

  @RabbitListener(bindings = @QueueBinding(
    value = @Queue(value = OrderEventConstants.ORDER_QUEUE_UPSERT_NAME),
    exchange = @Exchange(value = OrderEventConstants.ORDER_EXCHANGE_NAME, type = "direct"),
    key = {
      OrderEventConstants.ORDER_CREATED_EVENT_ROUTING_KEY,
      OrderEventConstants.ORDER_UPDATED_EVENT_ROUTING_KEY
    }
  ))
  public void onMessage(final OrderEventMessage message) {
    try {
     this.orderReadableService.save();
    } catch (Exception e) {
      throw new InternalError(e.getMessage());
    }
  }
}
