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
import com.deyvisonborges.service.orders.core.domain.pagination.Pagination;
import com.deyvisonborges.service.orders.core.domain.pagination.SearchDirection;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderPaginationQuery;

import java.util.Arrays;
import java.util.List;
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

  /**
   * Check endpoint state
   *
   * @return
   */
  public ResponseEntity<?> health() {
    return ResponseEntity.ok().body("Health check");
  }

  /**
   * Create a single order
   *
   * @param command
   */
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void createOrder(@RequestBody CreateOrderCommand command) {
    this.createOrderCommandHandler.handle(command);
  }

  /**
   * Update order
   *
   * @param orderId
   * @param command
   */
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

  /**
   * Return a single order by id
   *
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
   * <p>
   * * @param search
   * @param page
   * @param size      //   * @param sort
   * @param direction
   * @implNote GET /orders?search=term1,term2,term3&page=0&perPage=10&sort=id&direction=ASCENDANT
   */
  @GetMapping
  public List<?> listOrders(
    @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
    @RequestParam(name = "size", required = false, defaultValue = "10") final int size,
    @RequestParam(name = "direction", required = false, defaultValue = "ASCENDANT") final String direction
//    @RequestParam(name = "search", required = false, defaultValue = "") final String search
//    @RequestParam(name = "sort", required = false, defaultValue = "id") final String sort,
  ) {
//    List<String> terms = search.isEmpty() ? List.of() : Arrays.stream(search.split(","))
//      .map(String::trim) // Remova espaços em branco adicionais.
//      .filter(term -> !term.isEmpty() && term.matches("^[a-zA-Z0-9]+$")) // Ignore termos vazio e Aceite apenas termos que correspondam a uma expressão regular, por exemplo, contendo apenas caracteres alfanuméricos.
//      .collect(Collectors.toList());

    return this.listOrdersQueryHandler.handle(
      new OrderPaginationQuery(
        // o sort ta null temporally
        page, size, null, null, SearchDirection.from(direction)
      )
    );
  }
}
