package com.deyvisonborges.service.orders.core.modules.management.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import com.deyvisonborges.service.orders.core.domain.AggregateRoot;

public class Order extends AggregateRoot<OrderID> {
  private OrderStatus status;
  private Set<OrderItem> items;
  private String customerId;
  private BigDecimal shippingFee;
  private BigDecimal discount;
  private BigDecimal subTotal;
  private BigDecimal total;
  private Currency currency;

  public Order(
    final OrderStatus status,
    final Set<OrderItem> items,
    final String customerId,
    final BigDecimal shippingFee,
    final BigDecimal discount,
    final Currency currency
  ) {
    super(OrderID.generate(OrderID.class), true, Instant.now(), Instant.now());
    this.status = status;
    this.items = items;
    this.customerId = customerId;
    this.shippingFee = shippingFee;
    this.discount = discount;
    this.currency = currency;
  }

  public Order(
    final OrderID id,
    final OrderStatus status,
    final Set<OrderItem> items,
    final String customerId,
    final BigDecimal shippingFee,
    final BigDecimal discount,
    final Currency currency
  ) {
    super(id, true, Instant.now(), Instant.now());
    this.status = status;
    this.items = items;
    this.customerId = customerId;
    this.shippingFee = shippingFee;
    this.discount = discount;
    this.currency = currency;
  }

  public static Order factory(
    final OrderStatus status,
    final Set<OrderItem> items,
    final String customerId,
    final BigDecimal subTotal,
    final BigDecimal shippingFee,
    final BigDecimal discount,
    final Currency currency
  ){
    return new Order(
      status, 
      items, 
      customerId, 
      shippingFee, 
      discount,
      currency
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

  public BigDecimal getSubTotal() {
    calculateSubTotal();
    return subTotal;
  }

  public BigDecimal getShippingFee() {
    return shippingFee;
  }

  public BigDecimal getDiscount() {
    return discount;
  }

  public BigDecimal getTotal() {
    calculateTotal();
    return total;
  }

  public Currency getCurrency() {
    return this.currency;
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

  public void setSubTotal(BigDecimal subTotal) {
    this.subTotal = subTotal;
  }

  public void setShippingFee(BigDecimal shippingFee) {
    this.shippingFee = shippingFee;
  }

  public void setDiscount(BigDecimal discount) {
    this.discount = discount;
  }

  public void setTotal(BigDecimal total) {
    this.total = total;
  }

  public void setCurrency(final Currency currency) {
    this.currency = currency;
  }

  public void calculateSubTotal() {
    this.subTotal = items.stream()
      .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
      .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public void calculateTotal() {
    if (subTotal == null) {
      calculateSubTotal();
    }
    this.total = subTotal.add(shippingFee).subtract(discount);
  }
}
