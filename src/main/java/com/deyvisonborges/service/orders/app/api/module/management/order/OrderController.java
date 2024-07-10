package com.deyvisonborges.service.orders.app.api.module.management.order;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.pagination.OrderFilterService;
import org.springframework.web.bind.annotation.RestController;

import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder.CreateOrderCommand;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder.CreateOrderLocalOrchestratorService;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.deleteorder.DeleteOrderCommandHandler;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.getorderbyid.GetOrderByIdOutput;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.getorderbyid.GetOrderByIdQueryHandler;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.listorders.ListOrdersQueryHandler;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.listorders.ListOrdersQueryOutput;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.updateorder.UpdateOrderCommand;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.updateorder.UpdateOrderCommandHandler;
import com.deyvisonborges.service.orders.core.domain.pagination.Pagination;
import com.deyvisonborges.service.orders.core.domain.pagination.SearchDirection;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderPaginationQuery;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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

@Tag(name = "Orders", description = "API for managing orders")
@RestController
@RequestMapping("/orders")
public class OrderController {
  private final CreateOrderLocalOrchestratorService createOrderOrchestrator;
  private final UpdateOrderCommandHandler updateOrderCommandHandler;
  private final DeleteOrderCommandHandler deleteOrderCommandHandler;
  private final GetOrderByIdQueryHandler getOrderByIdQueryHandler;
  private final ListOrdersQueryHandler listOrdersQueryHandler;

  public OrderController(
    final CreateOrderLocalOrchestratorService createOrderOrchestrator,
    final UpdateOrderCommandHandler updateOrderCommandHandler,
    final DeleteOrderCommandHandler deleteOrderCommandHandler,
    final GetOrderByIdQueryHandler getOrderByIdQueryHandler,
    final ListOrdersQueryHandler listOrdersQueryHandler
  ) {
    this.createOrderOrchestrator = createOrderOrchestrator;
    this.updateOrderCommandHandler = updateOrderCommandHandler;
    this.deleteOrderCommandHandler = deleteOrderCommandHandler;
    this.getOrderByIdQueryHandler = getOrderByIdQueryHandler;
    this.listOrdersQueryHandler = listOrdersQueryHandler;
  }

  @Operation(summary = "Create a new order", description = "Creates a new order in the system")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Order created successfully"),
    @ApiResponse(responseCode = "422", description = "Validation error occurred"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public void create(@RequestBody CreateOrderCommand command) {
    this.createOrderOrchestrator.execute(command);
  }

  @Operation(summary = "Update an existing order", description = "Updates an existing order by ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Order updated successfully"),
    @ApiResponse(responseCode = "404", description = "Order not found"),
    @ApiResponse(responseCode = "422", description = "Validation error occurred"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping(value = "{id}")
  public void update(@Parameter(description = "ID of the order to be updated") @PathVariable("id") String orderId, 
                     @RequestBody UpdateOrderCommand command) {
    this.updateOrderCommandHandler.handle(orderId, command);
  }

  @Operation(summary = "Delete an existing order", description = "Deletes an existing order by ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "204", description = "Order deleted successfully"),
    @ApiResponse(responseCode = "404", description = "Order not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping(value = "{id}")
  public void delete(@Parameter(description = "ID of the order to be deleted") @PathVariable("id") String orderId) {
    this.deleteOrderCommandHandler.handle(orderId);
  }

  @Operation(summary = "Get order by ID", description = "Fetches an order by its ID")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Order fetched successfully"),
    @ApiResponse(responseCode = "404", description = "Order not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<GetOrderByIdOutput> getOrderById(@Parameter(description = "ID of the order to be fetched") 
                                                         @PathVariable(name = "id") String id) {
    final var result = this.getOrderByIdQueryHandler.handle(id);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @Operation(summary = "List orders with pagination", description = "Lists orders with pagination, sorting, and filtering")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Orders listed successfully"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  @GetMapping
  public Pagination<ListOrdersQueryOutput> findAllWithPagination(
    @Parameter(description = "Page number for pagination") @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
    @Parameter(description = "Page size for pagination") @RequestParam(name = "size", required = false, defaultValue = "10") final int size,
    @Parameter(description = "Sort direction (ASC/DESC)") @RequestParam(name = "direction", required = false, defaultValue = "ASC") final String direction,
    @Parameter(description = "Field to sort by") @RequestParam(name = "sort", required = false, defaultValue = "id") final String sort,
    @Parameter(description = "Filters to apply") @RequestParam(name = "filters", required = false) final String filters
  ) {
    final var mappedFilters = OrderFilterService.parseFilters(filters);
    return this.listOrdersQueryHandler.handle(
      new OrderPaginationQuery(page, size, sort, SearchDirection.from(direction), mappedFilters)
    );
  }
}
