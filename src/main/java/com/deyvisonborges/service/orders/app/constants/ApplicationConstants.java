package com.deyvisonborges.service.orders.app.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class ApplicationConstants {
  @Value("${spring.rabbitmq.host}")
	public String RABBITMQ_HOST;

  public String getUsername() {
    return this.RABBITMQ_HOST;
  }
}


