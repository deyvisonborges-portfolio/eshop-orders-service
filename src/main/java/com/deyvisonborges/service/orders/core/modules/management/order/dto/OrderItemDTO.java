package com.deyvisonborges.service.orders.core.modules.management.order.dto;

import java.time.Instant;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.domain.primitives.MoneyDTO;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderItem;

public record OrderItemDTO(
  String id,
  boolean active,
  Instant createdAt,
  Instant updatedAt,
  String productId,
  int quantity,
  MoneyDTO price
) {
  public OrderItem toAggregate() {
    return OrderItem.factory(
      this.id(), 
      this.active(), 
      this.createdAt(), 
      this.updatedAt(), 
      this.productId(), 
      this.quantity(), 
      new Money(this.price().amount(), this.price().currency())
    );
  }
}
