package com.deyvisonborges.service.orders;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.ApplicationContext;
// import org.springframework.context.annotation.Bean;

// import com.deyvisonborges.service.orders.core.domain.cqrs.springcqrsmodule.Bus;
// import com.deyvisonborges.service.orders.core.domain.cqrs.springcqrsmodule.Registry;
// import com.deyvisonborges.service.orders.core.domain.cqrs.springcqrsmodule.SpringBus;

@SpringBootApplication
@EnableRabbit
public class OrdersApplication {
  public static void main(String[] args) {
    SpringApplication.run(OrdersApplication.class, args);
  }

  /**
   * Remove temporally my custom Spring CQRS Module
   */
  // @Bean
  // Registry registry(ApplicationContext applicationContext) {
  //   return new Registry(applicationContext);
  // }

  // @Bean
  // Bus commandBus(Registry registry) {
  //   return new SpringBus(registry);
  // }
}
