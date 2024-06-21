package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.getorderbyid;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import com.deyvisonborges.service.orders.core.modules.management.order.Currency;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;
import com.deyvisonborges.service.orders.core.modules.management.order.dto.OrderItemDTO;

public record GetOrderByIdOutput(
  String id,
  boolean active,
  Instant createdAt,
  OrderStatus status,
  Set<OrderItemDTO> items,
  String customerId,
  BigDecimal subTotal,
  BigDecimal shippingFee,
  BigDecimal discount,
  BigDecimal total,
  Currency currency
) {
  public static GetOrderByIdOutput from(final Order order) {
    return new GetOrderByIdOutput(
      order.getId().getValue(),
      order.getActive(),
      order.getCreatedAt(),
      order.getStatus(),
      OrderItemDTO.fromSet(order.getItems()), 
      order.getCustomerId(), 
      order.getSubTotal(), 
      order.getShippingFee(), 
      order.getDiscount(), 
      order.getTotal(),
      order.getCurrency()
     );
  }
}
