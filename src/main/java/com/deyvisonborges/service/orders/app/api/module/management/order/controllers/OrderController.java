package com.deyvisonborges.service.orders.app.api.module.management.order.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder.CreateOrderCommand;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder.CreateOrderCommandHandler;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.deleteorder.DeleteOrderCommandHandler;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.getorderbyid.GetOrderByIdOutput;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.getorderbyid.GetOrderByIdQueryHandler;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.listorders.ListOrdersQueryHandler;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.updateorder.UpdateOrderCommand;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.updateorder.UpdateOrderCommandHandler;
import com.deyvisonborges.service.orders.core.domain.pagination.SearchDirection;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderPaginationQuery;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final CreateOrderCommandHandler createOrderCommandHandler;
  private final UpdateOrderCommandHandler updateOrderCommandHandler;
  private final DeleteOrderCommandHandler deleteOrderCommandHandler;
  private final GetOrderByIdQueryHandler getOrderByIdQueryHandler;
  private final ListOrdersQueryHandler listOrdersQueryHandler;

  public OrderController(
    final CreateOrderCommandHandler createOrderCommandHandler,
    final UpdateOrderCommandHandler updateOrderCommandHandler,
    final DeleteOrderCommandHandler deleteOrderCommandHandler,
    final GetOrderByIdQueryHandler getOrderByIdQueryHandler,
    final ListOrdersQueryHandler listOrdersQueryHandler
  ) {
    this.createOrderCommandHandler = createOrderCommandHandler;
    this.updateOrderCommandHandler = updateOrderCommandHandler;
    this.deleteOrderCommandHandler = deleteOrderCommandHandler;
    this.getOrderByIdQueryHandler = getOrderByIdQueryHandler;
    this.listOrdersQueryHandler = listOrdersQueryHandler;
  }

  public ResponseEntity<?> health() {
    return ResponseEntity.ok().body("Health check");
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void createOrder(@RequestBody CreateOrderCommand command) {
    this.createOrderCommandHandler.handle(command);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping(value = "{id}")
  public void updateOrder(@PathVariable("id") String orderId, @RequestBody UpdateOrderCommand command) {
    this.updateOrderCommandHandler.handle(orderId, command);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping
  public void deleteOrder(@PathVariable("id") String orderId) {
    this.deleteOrderCommandHandler.handle(orderId);
  }

  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetOrderByIdOutput> getOrderById(@PathVariable(name = "id") String id) {
    final var result = this.getOrderByIdQueryHandler.handle(id);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @GetMapping
  public List<?> listOrders(
    @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
    @RequestParam(name = "size", required = false, defaultValue = "10") final int size,
    @RequestParam(name = "direction", required = false, defaultValue = "ASCENDANT") final String direction,
    @RequestParam Map<String, String> search,
    @RequestParam(name = "sort", required = false, defaultValue = "id") final String sort
  ) {
    Map<String, String> terms = search.entrySet().stream()
      .filter(entry -> entry.getKey().startsWith("search"))
      .map(entry -> entry.getValue().split("="))
      .collect(Collectors.toMap(parts -> parts[0], parts -> parts[1]));

    return this.listOrdersQueryHandler.handle(
      new OrderPaginationQuery(
        page, size, terms, sort, SearchDirection.from(direction)
      )
    ).items();
  }
}
