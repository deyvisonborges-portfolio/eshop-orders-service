package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.listorders;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

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
    Set<OrderItemDTO> itemDTOs = order.getItems().stream()
      .map((orderItem) -> {
        orderItem.setUpdatedAt(null);
        return OrderItemDTO.from(orderItem);
      })
      .collect(Collectors.toSet());

    return new ListOrdersQueryOutput(
      order.getId().getValue(),
      order.getActive(),
      order.getCreatedAt(),
      order.getStatus(),
      itemDTOs, 
      order.getCustomerId(), 
      order.getPaymentsIds(), 
      order.getSubTotal(), 
      order.getShippingFee(), 
      order.getDiscount(), 
      order.getTotal()
     );
  }
}
