package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.updateorder;

import java.math.BigDecimal;
import java.util.Set;

import com.deyvisonborges.service.orders.core.modules.management.order.Currency;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;
import com.deyvisonborges.service.orders.core.modules.management.order.dto.OrderItemDTO;

public record UpdateOrderCommand(
  OrderStatus status,
  Set<OrderItemDTO> items,
  String customerId,
  Set<String> paymentsIds,
  BigDecimal subTotal,
  BigDecimal shippingFee,
  BigDecimal discount,
  BigDecimal total,
  Currency currency
) {}
