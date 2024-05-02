package com.deyvisonborges.service.orders.app.messaging.artifacts;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.deyvisonborges.service.orders.core.modules.management.order.event.OrderEventConstants;

@Configuration
public class OrderRabbitMqConfig {
  @Bean
  DirectExchange orderDirectExchange() {
    return new DirectExchange(OrderEventConstants.ORDER_EXCHANGE_NAME);
  }

  @Bean
  Queue orderQueue() {
    final var isDurable = true;
    final var isExclusive = false;
    final var isAutoDelete = false;
    return new Queue(OrderEventConstants.ORDER_QUEUE_NAME, isDurable, isExclusive, isAutoDelete);
  }

  @Bean
  Binding orderCreatedOrderEvent() {
    return BindingBuilder
      .bind(orderQueue())
      .to(orderDirectExchange())
      .with(OrderEventConstants.ORDER_CREATED_EVENT_ROUTING_KEY);
  }
}
