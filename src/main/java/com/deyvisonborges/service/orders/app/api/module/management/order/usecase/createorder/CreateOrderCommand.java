package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import com.deyvisonborges.service.orders.core.modules.management.order.Currency;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderItem;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;
import com.deyvisonborges.service.orders.core.modules.management.order.dto.OrderItemDTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Command to create a new order")
public record CreateOrderCommand(
  @Schema(description = "Status of the order") OrderStatus status,
  @Schema(description = "Items of the order") Set<OrderItemDTO> items,
  @Schema(description = "ID of the customer") String customerId,
  @Schema(description = "Shipping fee for the order") BigDecimal shippingFee,
  @Schema(description = "Discount applied to the order") BigDecimal discount,
  @Schema(description = "Currency of the order") Currency currency
) {
  public static Order toAggregate(final CreateOrderCommand command) {
    Set<OrderItem> orderItems = command.items().stream()
      .map(OrderItemDTO::toAggregate)
      .collect(Collectors.toSet());

    return Order.factory(
      command.status(), 
      orderItems, 
      command.customerId(), 
      command.shippingFee(), 
      command.discount(),
      command.currency()
    );
  }
}
