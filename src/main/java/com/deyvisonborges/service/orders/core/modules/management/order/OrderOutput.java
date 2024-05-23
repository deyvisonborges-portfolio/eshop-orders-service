package com.deyvisonborges.service.orders.core.modules.management.order;

import java.time.Instant;
import java.util.Set;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;

public record OrderOutput(
  String id,
  boolean active,
  Instant createdAt,
  Instant updatedAt,
  OrderStatus status,
  Set<OrderItem> items,
  String customerId,
  Set<String> paymentsIds,
  Money subTotal,
  Money shippingFee,
  Money discount,
  Money total
) {
  public static OrderOutput from(final Order order) {
    return new OrderOutput(
      order.getId().getValue(),
      order.getActive(),
      order.getCreatedAt(),
      order.getUpdatedAt(),
      order.getStatus(),
      order.getItems(), 
      order.getCustomerId(), 
      order.getPaymentsIds(), 
      order.getSubTotal(), 
      order.getShippingFee(), 
      order.getDiscount(), 
      order.getTotal()
     );
  }
}
