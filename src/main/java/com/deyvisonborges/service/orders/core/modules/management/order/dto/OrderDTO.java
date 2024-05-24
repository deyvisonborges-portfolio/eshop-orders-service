package com.deyvisonborges.service.orders.core.modules.management.order.dto;

import java.time.Instant;
import java.util.Set;

import com.deyvisonborges.service.orders.core.domain.primitives.MoneyDTO;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderDTO(
  String id,
  boolean active,
  Instant createdAt,
  Instant updatedAt,
  OrderStatus status,
  Set<OrderItemDTO> items,
  String customerId,
  Set<String> paymentsIds,
  MoneyDTO subTotal,
  MoneyDTO shippingFee,
  MoneyDTO discount,
  MoneyDTO total
) {}
