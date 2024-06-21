package com.deyvisonborges.service.orders.core.modules.management.order;

import java.time.Instant;
import java.util.Set;

import com.deyvisonborges.service.orders.core.domain.AggregateRoot;
import com.deyvisonborges.service.orders.core.domain.primitives.Money;

public class Order extends AggregateRoot<OrderID> {
  private OrderStatus status;
  private Set<OrderItem> items;
  private String customerId;
  private Money subTotal;
  private Money shippingFee;
  private Money discount;
  private Money total;

  public Order(
    final OrderStatus status,
    final Set<OrderItem> items,
    final String customerId,
    final Money subTotal,
    final Money shippingFee,
    final Money discount
  ) {
    super(OrderID.generate(OrderID.class), true, Instant.now(), Instant.now());
    this.status = status;
    this.items = items;
    this.customerId = customerId;
    this.subTotal = subTotal;
    this.shippingFee = shippingFee;
    this.discount = discount;
  }

  public Order(
    final OrderID id,
    final OrderStatus status,
    final Set<OrderItem> items,
    final String customerId,
    final Money subTotal,
    final Money shippingFee,
    final Money discount
  ) {
    super(id, true, Instant.now(), Instant.now());
    this.status = status;
    this.items = items;
    this.customerId = customerId;
    this.subTotal = subTotal;
    this.shippingFee = shippingFee;
    this.discount = discount;
  }

  public static Order factory(
    final OrderStatus status,
    final Set<OrderItem> items,
    final String customerId,
    final Money subTotal,
    final Money shippingFee,
    final Money discount
  ){
    return new Order(
      status, 
      items, 
      customerId, 
      subTotal, 
      shippingFee, 
      discount
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

  public void setItems(Set<OrderItem> items) {
    this.items = items;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public void setSubTotal(Money subTotal) {
    this.subTotal = subTotal;
  }

  public void setShippingFee(Money shippingFee) {
    this.shippingFee = shippingFee;
  }

  public void setDiscount(Money discount) {
    this.discount = discount;
  }

  public void setTotal(Money total) {
    this.total = total;
  }
}
