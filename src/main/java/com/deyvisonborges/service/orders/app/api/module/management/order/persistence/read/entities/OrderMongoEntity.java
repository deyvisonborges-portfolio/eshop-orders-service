package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderID;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "orders")
public class OrderMongoEntity implements Serializable{
  @Id
  private String id;
  private Boolean active;

  @Field(name = "created_at")
  private Instant createdAt;

  @Field(name = "updated_at")
  private Instant updatedAt;

  private OrderStatus status;

  private Set<OrderItemMongoEntity> items = new HashSet<>();

  @Field(name = "customer_id")
  private String customerId;

  @Field(name = "shipping_fee_amount")
  private BigDecimal shippingFeeAmount;

  @Field(name = "shipping_fee_currency")
  private String shippingFeeCurrency;

  @Field(name = "subtotal_amount")
  private BigDecimal subTotalAmount;

  @Field(name = "subtotal_currency")
  private String subTotalCurrency;

  @Field(name = "discount_amount")
  private BigDecimal discountAmount;

  @Field(name = "discount_currency")
  private String discountCurrency;

  @Field(name = "total_amount")
  private BigDecimal totalAmount;

  @Field(name = "total_currency")
  private String totalCurrency;

  public OrderMongoEntity() {}

  public OrderMongoEntity(
    final String id, 
    final Boolean active, 
    final Instant createdAt, 
    final Instant updatedAt, 
    final OrderStatus status,
    final Set<OrderItemMongoEntity> items, 
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

  public static Order toAggregate(final OrderMongoEntity entity) {
    return new Order(
      new OrderID(entity.id),
      entity.status,
      OrderItemMongoEntity.toAggregateSet(entity.items),
      entity.customerId,
      new Money(entity.subTotalAmount, entity.subTotalCurrency),
      new Money(entity.shippingFeeAmount, entity.shippingFeeCurrency),
      new Money(entity.discountAmount, entity.discountCurrency),
      new Money(entity.totalAmount, entity.totalCurrency)
    );
  }

  public static OrderMongoEntity toMongoEntity(final Order order) {
    Set<OrderItemMongoEntity> orderItems = order.getItems().stream()
      .map(OrderItemMongoEntity::toMongoEntity)
      .collect(Collectors.toSet());

    return new OrderMongoEntity(
      order.getId().getValue(),
      order.getActive(),
      order.getCreatedAt(),
      order.getUpdatedAt(),
      order.getStatus(),
      orderItems,
      order.getCustomerId(),
      order.getSubTotal().getAmount(),
      order.getSubTotal().getCurrency(),
      order.getShippingFee().getAmount(),
      order.getShippingFee().getCurrency(),
      order.getDiscount().getAmount(),
      order.getDiscount().getCurrency(),
      order.getTotal().getAmount(),
      order.getTotal().getCurrency()
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

  public Set<OrderItemMongoEntity> getItems() {
    return items;
  }

  public void setItems(Set<OrderItemMongoEntity> items) {
    this.items = items;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }
}
