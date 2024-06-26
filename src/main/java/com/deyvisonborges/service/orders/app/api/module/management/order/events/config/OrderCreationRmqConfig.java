package com.deyvisonborges.service.orders.app.api.module.management.order.events.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEventConstants;

@Configuration
public class OrderCreationRmqConfig {
  @Bean
  Queue orderCreationQueue() {
    return new Queue(OrderEventConstants.ORDER_CREATION_QUEUE_NAME, true, false, false);
  }

  @Bean
  Binding orderCreationBinding() {
    return BindingBuilder
      .bind(orderCreationQueue())
      .to(OrderExchangeConfig.orderDirectExchange())
      .with(OrderEventConstants.ORDER_CREATION_EVENT_ROUTING_KEY);
  }

  @Bean
  Queue orderCreationCompensationQueue() {
    return new Queue(OrderEventConstants.ORDER_CREATION_COMPENSATION_QUEUE, true, false, false);
  }

  @Bean
  Binding orderCreationCompensationBinding() {
    return BindingBuilder
      .bind(orderCreationCompensationQueue())
      .to(OrderExchangeConfig.orderDirectExchange())
      .with(OrderEventConstants.ORDER_CREATION_COMPENSATION_QUEUE);
  }
}
