package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.getorderbyid;

import java.time.Instant;
import java.util.Set;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderItem;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;

public record GetOrderByIdOutput(
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
  public static GetOrderByIdOutput from(final Order order) {
    return new GetOrderByIdOutput(
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
