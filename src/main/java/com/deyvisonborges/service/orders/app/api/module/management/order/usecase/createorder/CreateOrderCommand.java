package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import java.util.Set;
import java.util.stream.Collectors;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderItem;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;
import com.deyvisonborges.service.orders.core.modules.management.order.dto.OrderItemDTO;

public record CreateOrderCommand(
  OrderStatus status,
  Set<OrderItemDTO> items,
  String customerId,
  Set<String> paymentsIds,
  Money subTotal,
  Money shippingFee,
  Money discount,
  Money total
) {
  public static Order toAggregate(final CreateOrderCommand command) {
    Set<OrderItem> orderItems = command.items().stream()
      .map(OrderItemDTO::toAggregate)
      .collect(Collectors.toSet());

    return Order.factory(
      command.status(), 
      orderItems, 
      command.customerId(), 
      command.paymentsIds(),
      command.subTotal(), 
      command.shippingFee(), 
      command.discount(),
      command.total()
    );
  }
}
