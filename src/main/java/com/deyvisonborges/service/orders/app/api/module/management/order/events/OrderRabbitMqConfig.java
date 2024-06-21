package com.deyvisonborges.service.orders.app.api.module.management.order.events;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRabbitMqConfig {
  @Bean
  DirectExchange orderDirectExchange() {
    return new DirectExchange(OrderEventConstants.ORDER_EXCHANGE_NAME);
  }

  @Bean
  Queue orderUpsertQueue() {
    final var isDurable = true;
    final var isExclusive = false;
    final var isAutoDelete = false;
    return new Queue(OrderEventConstants.ORDER_QUEUE_UPSERT_NAME, isDurable, isExclusive, isAutoDelete);
  }

  @Bean
  Queue orderCancelledQueue() {
    final var isDurable = true;
    final var isExclusive = false;
    final var isAutoDelete = false;
    return new Queue(OrderEventConstants.ORDER_QUEUE_CANCELLED_NAME, isDurable, isExclusive, isAutoDelete);
  }

  @Bean
  Queue orderCompensationQueue() {
    return new Queue(OrderEventConstants.ORDER_COMPENSATION_QUEUE, true);
  }

  @Bean
  Binding orderCompensationEventBinding() {
    return BindingBuilder
      .bind(orderCompensationQueue())
      .to(orderDirectExchange())
      .with(OrderEventConstants.ORDER_COMPENSATION_ROUTING_KEY);
  }

  @Bean
  Binding orderCreatedOrderEvent() {
    return BindingBuilder
      .bind(orderUpsertQueue())
      .to(orderDirectExchange())
      .with(OrderEventConstants.ORDER_CREATED_EVENT_ROUTING_KEY);
  }

  @Bean
  Binding orderCancelledOrderEvent() {
    return BindingBuilder
      .bind(orderCancelledQueue())
      .to(orderDirectExchange())
      .with(OrderEventConstants.ORDER_CANCELLED_EVENT_ROUTING_KEY);
  }
}
