package com.deyvisonborges.service.orders.core.modules.management.order.dto;

import java.util.Set;

import com.deyvisonborges.service.orders.core.domain.primitives.MoneyDTO;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;

public record OrderDTO(
  OrderStatus status,
  Set<OrderItemDTO> items,
  String customerId,
  Set<String> paymentsIds,
  MoneyDTO subTotal,
  MoneyDTO shippingFee,
  MoneyDTO discount,
  MoneyDTO total
) {}
