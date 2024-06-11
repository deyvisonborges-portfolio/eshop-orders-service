package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderID;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@RedisHash("orders")
public class OrderRedisEntity implements Serializable{
  @Id
  @Indexed
  private String id;
  private Boolean active;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @Reference
  private Set<OrderItemRedisEntity> items;

  @Column(name = "customer_id")
  private String customerId;

  @Column(name = "shipping_fee_amount")
  private BigDecimal shippingFeeAmount;

  @Column(name = "shipping_fee_currency")
  private String shippingFeeCurrency;

  @Column(name = "subtotal_amount")
  private BigDecimal subTotalAmount;

  @Column(name = "subtotal_currency")
  private String subTotalCurrency;

  @Column(name = "discount_amount")
  private BigDecimal discountAmount;

  @Column(name = "discount_currency")
  private String discountCurrency;

  @Column(name = "total_amount")
  private BigDecimal totalAmount;

  @Column(name = "total_currency")
  private String totalCurrency;

  public OrderRedisEntity() {}

  public OrderRedisEntity(
    final String id, 
    final Boolean active, 
    final Instant createdAt, 
    final Instant updatedAt, 
    final OrderStatus status,
    final Set<OrderItemRedisEntity> items, 
    final String customerId, 
    final BigDecimal shippingFeeAmount, 
    final String shippingFeeCurrency,
    final BigDecimal subTotalAmount, 
    final String subTotalCurrency, 
    final BigDecimal discountAmount, 
    final String discountCurrency,
    final BigDecimal totalAmount, 
    final String totalCurrency
  ) {
    this.id = id;
    this.active = active;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.status = status;
    this.items = items;
    this.customerId = customerId;
    this.shippingFeeAmount = shippingFeeAmount;
    this.shippingFeeCurrency = shippingFeeCurrency;
    this.subTotalAmount = subTotalAmount;
    this.subTotalCurrency = subTotalCurrency;
    this.discountAmount = discountAmount;
    this.discountCurrency = discountCurrency;
    this.totalAmount = totalAmount;
    this.totalCurrency = totalCurrency;
  }

  public static Order toAggregate(final OrderRedisEntity entity) {
    return new Order(
      new OrderID(entity.id),
      entity.status,
      OrderItemRedisEntity.toAggregateSet(entity.items),
      entity.customerId,
      new Money(entity.subTotalAmount, entity.subTotalCurrency),
      new Money(entity.shippingFeeAmount, entity.shippingFeeCurrency),
      new Money(entity.discountAmount, entity.discountCurrency),
      new Money(entity.totalAmount, entity.totalCurrency)
    );
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public Set<OrderItemRedisEntity> getItems() {
    return items;
  }

  public void setItems(Set<OrderItemRedisEntity> items) {
    this.items = items;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public BigDecimal getShippingFeeAmount() {
    return shippingFeeAmount;
  }

  public void setShippingFeeAmount(BigDecimal shippingFeeAmount) {
    this.shippingFeeAmount = shippingFeeAmount;
  }

  public String getShippingFeeCurrency() {
    return shippingFeeCurrency;
  }

  public void setShippingFeeCurrency(String shippingFeeCurrency) {
    this.shippingFeeCurrency = shippingFeeCurrency;
  }

  public BigDecimal getSubTotalAmount() {
    return subTotalAmount;
  }

  public void setSubTotalAmount(BigDecimal subTotalAmount) {
    this.subTotalAmount = subTotalAmount;
  }

  public String getSubTotalCurrency() {
    return subTotalCurrency;
  }

  public void setSubTotalCurrency(String subTotalCurrency) {
    this.subTotalCurrency = subTotalCurrency;
  }

  public BigDecimal getDiscountAmount() {
    return discountAmount;
  }

  public void setDiscountAmount(BigDecimal discountAmount) {
    this.discountAmount = discountAmount;
  }

  public String getDiscountCurrency() {
    return discountCurrency;
  }

  public void setDiscountCurrency(String discountCurrency) {
    this.discountCurrency = discountCurrency;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getTotalCurrency() {
    return totalCurrency;
  }

  public void setTotalCurrency(String totalCurrency) {
    this.totalCurrency = totalCurrency;
  }
}
