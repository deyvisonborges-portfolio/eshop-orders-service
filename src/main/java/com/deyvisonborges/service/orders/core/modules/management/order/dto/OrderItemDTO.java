package com.deyvisonborges.service.orders.core.modules.management.order.dto;

import java.time.Instant;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;

public record OrderItemDTO(
  String id,
  boolean active,
  Instant createdAt,
  Instant updatedAt,
  String productId,
  int quantity,
  Money price
) {
}
