package com.deyvisonborges.service.orders.core.modules.management.order;

import java.util.Set;

import com.deyvisonborges.service.orders.core.primitives.Money;

public class Order {
  private OrderStatus status;
  private Set<OrderItem> items;
  private String customerId;
  private Set<String> paymentsIds;
  private Money subTotal;
  private Money shippingFee;
  private Money discount;
  private Money total;

  public Order(
    final OrderStatus status,
    final Set<OrderItem> items,
    final String customerId,
    final Set<String> paymentsIds,
    final Money subTotal,
    final Money shippingFee,
    final Money discount,
    final Money total
  ) {
    this.status = status;
    this.items = items;
    this.customerId = customerId;
    this.paymentsIds = paymentsIds;
    this.subTotal = subTotal;
    this.shippingFee = shippingFee;
    this.discount = discount;
    this.total = total;
  }
}
