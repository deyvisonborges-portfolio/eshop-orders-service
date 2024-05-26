package com.deyvisonborges.service.orders.core.modules.management.order.dto;

import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.domain.primitives.MoneyDTO;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderItem;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
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
    return new OrderItem(
      this.productId(), 
      this.quantity(), 
      new Money(this.price().amount(), this.price().currency())
    );
  }

  public static OrderItemDTO from(OrderItem item) {
    return new OrderItemDTO(
      item.getId().getValue(),
      item.getActive(),
      item.getCreatedAt(),
      item.getUpdatedAt(),
      item.getProductId(),
      item.getQuantity(),
      new MoneyDTO(item.getPrice().getAmount(), item.getPrice().getCurrency())
    );
  }

  public static Set<OrderItemDTO> fromSet(final Set<OrderItem> items) {
    return items.stream()
      .map((orderItem) -> {
        orderItem.setUpdatedAt(null);
        return OrderItemDTO.from(orderItem);
      })
      .collect(Collectors.toSet());
  }
}
