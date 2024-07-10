package com.deyvisonborges.service.orders.core.modules.management.order.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import com.deyvisonborges.service.orders.core.modules.management.order.Currency;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Data Transfer Object for orders")
public record OrderDTO(
  @Schema(description = "ID of the order") String id,
  @Schema(description = "Whether the order is active") boolean active,
  @Schema(description = "Creation timestamp of the order") Instant createdAt,
  @Schema(description = "Last update timestamp of the order") Instant updatedAt,
  @Schema(description = "Status of the order") OrderStatus status,
  @Schema(description = "Items of the order") Set<OrderItemDTO> items,
  @Schema(description = "ID of the customer") String customerId,
  @Schema(description = "Shipping fee for the order") BigDecimal shippingFee,
  @Schema(description = "Discount applied to the order") BigDecimal discount,
  @Schema(description = "Subtotal of the order") BigDecimal subTotal,
  @Schema(description = "Total amount of the order") BigDecimal total,
  @Schema(description = "Currency of the order") Currency currency
) {}
