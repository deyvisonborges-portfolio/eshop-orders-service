package com.deyvisonborges.service.orders.app.api.module.management.order.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder.CreateOrderCommand;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder.CreateOrderCommandHandler;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/orders")
public class OrderCommandsController {
  private final CreateOrderCommandHandler createOrderCommandHandler;

  public OrderCommandsController(final CreateOrderCommandHandler createOrderCommandHandler) {
    this.createOrderCommandHandler = createOrderCommandHandler;
  }

  @PostMapping
  public void createOrder(@RequestBody CreateOrderCommand command) {
    this.createOrderCommandHandler.handle(command);
  }  
}
