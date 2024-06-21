package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
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
  Money subTotal,
  Money shippingFee,
  Money discount
) {
  public static Order toAggregate(final CreateOrderCommand command) {
    Set<OrderItem> orderItems = command.items().stream()
      .map(OrderItemDTO::toAggregate)
      .collect(Collectors.toSet());

    final var order = Order.factory(
      command.status(), 
      orderItems, 
      command.customerId(), 
      command.subTotal(), 
      command.shippingFee(), 
      command.discount()
    );

    order.setTotal(new Money(getOrderTotal(command.items()), getOrderCurrency(command.items)));
    return order;
  }

  private static BigDecimal getOrderTotal(final Set<OrderItemDTO> items) {
    return items
      .stream()
      .map(i -> i.price().amount().multiply(BigDecimal.valueOf(i.quantity())))
      .reduce(BigDecimal::add)
      .orElse(BigDecimal.ZERO);
  }

  private static String getOrderCurrency(final Set<OrderItemDTO> items) {
    return items.stream()
      .map(i -> i.price().currency())
      .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
      .entrySet().stream()
      .max(Map.Entry.comparingByValue())
      .map(Map.Entry::getKey)
      .orElse(null);
  }
}
