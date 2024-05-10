package com.deyvisonborges.service.orders.app.messaging.client.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqEventEmitter {
  private final AmqpTemplate template;

  public RabbitmqEventEmitter(AmqpTemplate template) {
    this.template = template;
  }

  public void emit(final String exchange, final String routingKey, final Object message) {
    this.template.convertAndSend(exchange, routingKey, message);
  }
}
