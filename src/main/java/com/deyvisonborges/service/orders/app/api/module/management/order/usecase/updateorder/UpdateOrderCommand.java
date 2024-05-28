package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.updateorder;

import java.util.Set;

import com.deyvisonborges.service.orders.core.domain.primitives.MoneyDTO;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;
import com.deyvisonborges.service.orders.core.modules.management.order.dto.OrderItemDTO;

public record UpdateOrderCommand(
  OrderStatus status,
  Set<OrderItemDTO> items,
  String customerId,
  Set<String> paymentsIds,
  MoneyDTO subTotal,
  MoneyDTO shippingFee,
  MoneyDTO discount,
  MoneyDTO total
) {}
