package com.deyvisonborges.service.orders.app.api.module.management.order.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder.CreateOrderCommand;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder.CreateOrderCommandHandler;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.getorderbyid.GetOrderByIdOutput;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.getorderbyid.GetOrderByIdQueryHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final CreateOrderCommandHandler createOrderCommandHandler;
  private final GetOrderByIdQueryHandler getOrderByIdQueryHandler;

  public OrderController(
      final CreateOrderCommandHandler createOrderCommandHandler,
      final GetOrderByIdQueryHandler getOrderByIdQueryHandler) {
    this.createOrderCommandHandler = createOrderCommandHandler;
    this.getOrderByIdQueryHandler = getOrderByIdQueryHandler;
  }

  public ResponseEntity<?> health() {
    return ResponseEntity.ok().body("Health check");
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping(
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public void createOrder(@RequestBody CreateOrderCommand command) {
    this.createOrderCommandHandler.handle(command);
  }

  @GetMapping(value = "{id}", 
    produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<GetOrderByIdOutput> getOrderById(@PathVariable(name = "id") String id) {
    final var result = this.getOrderByIdQueryHandler.handle(id);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }
}
