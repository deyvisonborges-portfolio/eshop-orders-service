package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.listorders;

import java.time.Instant;
import java.util.Set;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;
import com.deyvisonborges.service.orders.core.modules.management.order.dto.OrderItemDTO;

public record ListOrdersQueryOutput(
  String id,
  boolean active,
  Instant createdAt,
  OrderStatus status,
  Set<OrderItemDTO> items,
  String customerId,
  Set<String> paymentsIds,
  Money subTotal,
  Money shippingFee,
  Money discount,
  Money total
) {
  public static ListOrdersQueryOutput from(final Order order) {
    return new ListOrdersQueryOutput(
      order.getId().getValue(),
      order.getActive(),
      order.getCreatedAt(),
      order.getStatus(),
      OrderItemDTO.fromSet(order.getItems()), 
      order.getCustomerId(), 
      order.getPaymentsIds(), 
      order.getSubTotal(), 
      order.getShippingFee(), 
      order.getDiscount(), 
      order.getTotal()
     );
  }
}
