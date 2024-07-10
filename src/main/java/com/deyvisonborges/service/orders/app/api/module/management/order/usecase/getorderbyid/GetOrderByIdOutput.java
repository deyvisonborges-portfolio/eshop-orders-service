package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.getorderbyid;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import com.deyvisonborges.service.orders.core.modules.management.order.Currency;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;
import com.deyvisonborges.service.orders.core.modules.management.order.dto.OrderItemDTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Output data for fetching an order by ID")
public record GetOrderByIdOutput(
  @Schema(description = "ID of the order") String id,
  @Schema(description = "Whether the order is active") boolean active,
  @Schema(description = "Creation timestamp of the order") Instant createdAt,
  @Schema(description = "Status of the order") OrderStatus status,
  @Schema(description = "Items of the order") Set<OrderItemDTO> items,
  @Schema(description = "ID of the customer") String customerId,
  @Schema(description = "Subtotal of the order") BigDecimal subTotal,
  @Schema(description = "Shipping fee for the order") BigDecimal shippingFee,
  @Schema(description = "Discount applied to the order") BigDecimal discount,
  @Schema(description = "Total amount of the order") BigDecimal total,
  @Schema(description = "Currency of the order") Currency currency
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
