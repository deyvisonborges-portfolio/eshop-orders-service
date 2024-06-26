package com.deyvisonborges.service.orders.app.api.module.management.order.events.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEventConstants;

@Configuration
public class OrderExchangeConfig {
  @Bean
  public static DirectExchange orderDirectExchange() {
    return new DirectExchange(OrderEventConstants.ORDER_EXCHANGE_NAME);
  }  
}
