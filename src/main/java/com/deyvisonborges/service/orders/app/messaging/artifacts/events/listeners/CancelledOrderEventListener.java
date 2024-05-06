package com.deyvisonborges.service.orders.app.messaging.artifacts.events.listeners;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderRepository;
import com.deyvisonborges.service.orders.app.messaging.artifacts.events.OrderEventConstants;
import com.deyvisonborges.service.orders.app.messaging.artifacts.events.OrderEventMessage;

@Component
public class CancelledOrderEventListener {
  private final OrderRepository orderRepository;

  public CancelledOrderEventListener(final OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }
  @RabbitListener(bindings = @QueueBinding(
    value = @Queue(value = OrderEventConstants.ORDER_QUEUE_NAME),
    exchange = @Exchange(value = OrderEventConstants.ORDER_EXCHANGE_NAME, type = "direct"),
    key = OrderEventConstants.ORDER_CANCELLED_EVENT_ROUTING_KEY)
  )
  public void onMessage(final OrderEventMessage message) {
    this.orderRepository.deleteById(message.getOrderId());
  }
}
