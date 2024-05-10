package com.deyvisonborges.service.orders;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@EnableRabbit
@SpringBootApplication
public class OrdersApplication {
  public static void main(String[] args) {
    System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "dev");
    SpringApplication.run(OrdersApplication.class, args);
  }
}
