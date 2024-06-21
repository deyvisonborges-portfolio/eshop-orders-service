package com.deyvisonborges.service.orders.core.modules.management.order.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import com.deyvisonborges.service.orders.core.modules.management.order.Currency;
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
  BigDecimal shippingFee,
  BigDecimal discount,
  BigDecimal subTotal,
  BigDecimal total,
  Currency currency
) {}
