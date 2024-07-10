package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.updateorder;

import java.math.BigDecimal;
import java.util.Set;

import com.deyvisonborges.service.orders.core.modules.management.order.Currency;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;
import com.deyvisonborges.service.orders.core.modules.management.order.dto.OrderItemDTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Command to update an existing order")
public record UpdateOrderCommand(
  @Schema(description = "Status of the order") OrderStatus status,
  @Schema(description = "Items of the order") Set<OrderItemDTO> items,
  @Schema(description = "ID of the customer") String customerId,
  @Schema(description = "IDs of the payments associated with the order") Set<String> paymentsIds,
  @Schema(description = "Subtotal of the order") BigDecimal subTotal,
  @Schema(description = "Shipping fee for the order") BigDecimal shippingFee,
  @Schema(description = "Discount applied to the order") BigDecimal discount,
  @Schema(description = "Total amount of the order") BigDecimal total,
  @Schema(description = "Currency of the order") Currency currency
) {}
