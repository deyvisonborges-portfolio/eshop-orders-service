package com.deyvisonborges.service.orders.core.modules.management.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import com.deyvisonborges.service.orders.core.AggregateRoot;
import com.deyvisonborges.service.orders.core.primitives.Money;

public class Order extends AggregateRoot<OrderID> {
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
    super(OrderID.generate(OrderID.class), true, Instant.now(), Instant.now());
    this.status = status;
    this.items = items;
    this.customerId = customerId;
    this.paymentsIds = paymentsIds;
    this.subTotal = subTotal;
    this.shippingFee = shippingFee;
    this.discount = discount;
    this.total = total;
  }

  public static Order emptyFactory() {
    return new Order(
      OrderStatus.CREATED, 
      Set.of(),
      UUID.randomUUID().toString(),
      Set.of(),
      new Money(BigDecimal.valueOf(100.00), "BRL"),
      new Money(BigDecimal.valueOf(15.00), "BRL"),
      new Money(BigDecimal.valueOf(5.45), "BRL"),
      new Money(BigDecimal.valueOf(230.00), "BRL")
    );
  }

  public Order clone() {
    return new Order(
      this.status,
      this.items,
      this.customerId,
      this.paymentsIds,
      this.subTotal,
      this.shippingFee,
      this.discount,
      this.total
    );
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  public void setItems(Set<OrderItem> items) {
    this.items = items;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public Set<String> getPaymentsIds() {
    return paymentsIds;
  }

  public void setPaymentsIds(Set<String> paymentsIds) {
    this.paymentsIds = paymentsIds;
  }

  public Money getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(Money subTotal) {
    this.subTotal = subTotal;
  }

  public Money getShippingFee() {
    return shippingFee;
  }

  public void setShippingFee(Money shippingFee) {
    this.shippingFee = shippingFee;
  }

  public Money getDiscount() {
    return discount;
  }

  public void setDiscount(Money discount) {
    this.discount = discount;
  }

  public Money getTotal() {
    return total;
  }

  public void setTotal(Money total) {
    this.total = total;
  }
}
