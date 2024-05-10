package com.deyvisonborges.service.orders.app.messaging.client.rabbitmq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
 class RabbitMqConfig {
  private final RabbitMqConstants rabbitMqConstants;

  public RabbitMqConfig(final RabbitMqConstants rabbitMqConstants) {
    this.rabbitMqConstants = rabbitMqConstants;
  }

  @Bean
  ConnectionFactory defaultConnection() {
    try {
			CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
			connectionFactory.setHost(this.rabbitMqConstants.getHost());
			connectionFactory.setPort(this.rabbitMqConstants.getPort());
			connectionFactory.setUsername(this.rabbitMqConstants.getUsername());
			connectionFactory.setPassword(this.rabbitMqConstants.getPassword());
			return connectionFactory;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
  }
}