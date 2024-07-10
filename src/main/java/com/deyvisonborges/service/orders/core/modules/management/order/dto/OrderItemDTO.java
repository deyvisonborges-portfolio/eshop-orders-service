package com.deyvisonborges.service.orders.core.modules.management.order.dto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import com.deyvisonborges.service.orders.core.modules.management.order.OrderItem;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Data Transfer Object for order items")
public record OrderItemDTO(
  @Schema(description = "ID of the order item") String id,
  @Schema(description = "ID of the product") String productId,
  @Schema(description = "Quantity of the product") int quantity,
  @Schema(description = "Price of the product") BigDecimal price
) {
  public OrderItem toAggregate() {
    return new OrderItem(
      this.productId(), 
      this.quantity(), 
      this.price()
    );
  }

  public static OrderItemDTO from(OrderItem item) {
    return new OrderItemDTO(
      item.getId().getValue(),
      item.getProductId(),
      item.getQuantity(),
      item.getPrice()
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
