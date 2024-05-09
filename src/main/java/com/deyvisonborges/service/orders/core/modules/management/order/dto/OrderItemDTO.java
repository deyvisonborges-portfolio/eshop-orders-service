package com.deyvisonborges.service.orders.core.modules.management.order.dto;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.domain.primitives.MoneyDTO;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderItem;

public record OrderItemDTO(
  String productId,
  int quantity,
  MoneyDTO price
) {
  public OrderItem toAggregate() {
    return new OrderItem(
      this.productId(), 
      this.quantity(), 
      new Money(this.price().amount(), this.price().currency())
    );
  }
}
