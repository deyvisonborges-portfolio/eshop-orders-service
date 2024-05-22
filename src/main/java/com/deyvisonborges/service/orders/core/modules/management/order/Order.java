package com.deyvisonborges.service.orders.core.modules.management.order;

import java.time.Instant;
import java.util.Set;

import com.deyvisonborges.service.orders.core.domain.AggregateRoot;
import com.deyvisonborges.service.orders.core.domain.primitives.Money;

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

  public Order(
    final OrderID id,
    final OrderStatus status,
    final Set<OrderItem> items,
    final String customerId,
    final Set<String> paymentsIds,
    final Money subTotal,
    final Money shippingFee,
    final Money discount,
    final Money total
  ) {
    super(id, true, Instant.now(), Instant.now());
    this.status = status;
    this.items = items;
    this.customerId = customerId;
    this.paymentsIds = paymentsIds;
    this.subTotal = subTotal;
    this.shippingFee = shippingFee;
    this.discount = discount;
    this.total = total;
  }

  public static Order factory(
    final OrderStatus status,
    final Set<OrderItem> items,
    final String customerId,
    final Set<String> paymentsIds,
    final Money subTotal,
    final Money shippingFee,
    final Money discount,
    final Money total
  ){
    return new Order(
      status, 
      items, 
      customerId, 
      paymentsIds, 
      subTotal, 
      shippingFee, 
      discount, 
      total
    );
  }

  @Override
  public OrderID getId() {
    return super.getId();
  }

  @Override
  public Boolean getActive() {
    return super.getActive();
  }

  @Override
  public Instant getCreatedAt() {
    return super.getCreatedAt();
  }

  @Override
  public Instant getUpdatedAt() {
    return super.getUpdatedAt();
  }

  public OrderStatus getStatus() {
    return status;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  public String getCustomerId() {
    return customerId;
  }

  public Set<String> getPaymentsIds() {
    return paymentsIds;
  }

  public Money getSubTotal() {
    return subTotal;
  }

  public Money getShippingFee() {
    return shippingFee;
  }

  public Money getDiscount() {
    return discount;
  }

  public Money getTotal() {
    return total;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }
}
