package com.deyvisonborges.service.orders.app.messaging.events.order;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderRabbitMqDeadLetterConfig {
 @Bean
  DirectExchange orderDlxExchange() {
    return new DirectExchange(OrderEventConstants.ORDER_DLX_EXCHANGE);
  }

  @Bean
  Queue orderDlq() {
    return new Queue(OrderEventConstants.ORDER_DQL_QUEUE); 
  }

  @Bean
  Binding orderDqlBinding() {
    return BindingBuilder
    .bind(orderDlq())
    .to(orderDlxExchange())
    .with(OrderEventConstants.ORDER_DQL_QUEUE);
  }  
}
