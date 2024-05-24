package com.deyvisonborges.service.orders.app.api.module.management.order.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder.CreateOrderCommand;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder.CreateOrderCommandHandler;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.getorderbyid.GetOrderByIdOutput;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.getorderbyid.GetOrderByIdQueryHandler;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.listorders.ListOrdersQueryHandler;
import com.deyvisonborges.service.orders.core.domain.pagination.old.Pagination;
import com.deyvisonborges.service.orders.core.domain.pagination.old.SearchDirection;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderPaginationQuery;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final CreateOrderCommandHandler createOrderCommandHandler;
  private final GetOrderByIdQueryHandler getOrderByIdQueryHandler;
  private final ListOrdersQueryHandler listOrdersQueryHandler;

  public OrderController(
    final CreateOrderCommandHandler createOrderCommandHandler,
    final GetOrderByIdQueryHandler getOrderByIdQueryHandler, 
    final ListOrdersQueryHandler listOrdersQueryHandler
  ) {
    this.createOrderCommandHandler = createOrderCommandHandler;
    this.getOrderByIdQueryHandler = getOrderByIdQueryHandler;
    this.listOrdersQueryHandler = listOrdersQueryHandler;
  }

  /**
   * Check endpoint state
   * @return
   */
  public ResponseEntity<?> health() {
    return ResponseEntity.ok().body("Health check");
  }

  /**
   * Create a single order
   * @param command
   */
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void createOrder(@RequestBody CreateOrderCommand command) {
    this.createOrderCommandHandler.handle(command);
  }

  /**
   * Return a single order by id
   * @param id
   * @return
   */
  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetOrderByIdOutput> getOrderById(@PathVariable(name = "id") String id) {
    final var result = this.getOrderByIdQueryHandler.handle(id);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  /**
   * Return all orders with pagination
   * @param search
   * @param page
   * @param perPage
   * @param sort
   * @param direction
   * @return
   */
  @GetMapping
  public Pagination<?> listOrders(
    @RequestParam(name = "search", required = false, defaultValue = "") final String search,
    @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
    @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
    @RequestParam(name = "sort", required = false, defaultValue = "id") final String sort,
    @RequestParam(name = "direction", required = false, defaultValue = "ASCENDANT") final String direction
  ) {
    return this.listOrdersQueryHandler.handle(
      new OrderPaginationQuery(
        page, perPage, search, sort, SearchDirection.from(direction)
      )
    );
  }
}
