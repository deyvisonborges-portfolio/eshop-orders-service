package com.deyvisonborges.service.orders.app.messaging.client.rabbitmq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class RabbitMqConstants {
  @Value("${spring.rabbitmq.host}")
	public String RABBITMQ_HOST;

	@Value("${spring.rabbitmq.username}")
	public String RABBITMQ_USERNAME;
	
  @Value("${spring.rabbitmq.password}")
	public String RABBITMQ_PASSWORD;
	
  @Value("${spring.rabbitmq.port}")
	public int RABBITMQ_PORT;

  public String getHost() {
    return this.RABBITMQ_HOST;
  }

  public String getUsername() {
    return this.RABBITMQ_USERNAME;
  }

  public String getPassword() {
    return this.RABBITMQ_PASSWORD;
  }

  public int getPort() {
    return this.RABBITMQ_PORT;
  }
}
