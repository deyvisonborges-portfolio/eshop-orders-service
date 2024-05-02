package com.deyvisonborges.service.orders.app.messaging.artifacts.events.listeners;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.deyvisonborges.service.orders.core.modules.management.order.event.OrderEventConstants;

@Component
public class CreateOrderEventListener implements MessageListener {
  @RabbitListener(bindings = @QueueBinding(
    value = @Queue(value = OrderEventConstants.ORDER_QUEUE_NAME),
    exchange = @Exchange(value = OrderEventConstants.ORDER_EXCHANGE_NAME, type = "direct"),
    key = OrderEventConstants.ORDER_CREATED_EVENT_ROUTING_KEY)
  )
  @Override
  public void onMessage(final Message message) {
    System.out.println("Received create order message: " + new String(message.getBody()));
  }
}
