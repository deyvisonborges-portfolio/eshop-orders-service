package com.deyvisonborges.service.orders;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.AbstractEnvironment;

@EnableRabbit
@EnableCaching
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
public class OrdersApplication {
  public static void main(String[] args) {
    System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "test");
    SpringApplication.run(OrdersApplication.class, args);
  }
}
