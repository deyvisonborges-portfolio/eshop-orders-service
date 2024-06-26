package com.deyvisonborges.service.orders.app.messaging.client.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class DlqPublisher {
  private final RabbitTemplate rabbitTemplate;

  public DlqPublisher(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public <T> void publishToDlq(String exchange, String routingKey, T message) {
    // Convert the generic object to a message
    @SuppressWarnings("null")
    Message amqpMessage = rabbitTemplate.getMessageConverter().toMessage(message, null);
    // Publish the message to DLQ
    rabbitTemplate.send(exchange, routingKey, amqpMessage);
  }
}
