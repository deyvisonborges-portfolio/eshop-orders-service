package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.mapping.Document;

import com.deyvisonborges.service.orders.core.modules.management.order.Currency;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderID;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "orders")
public class OrderMongoEntity implements Serializable{
  @MongoId
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

  @Field(name = "shipping_fee")
  private BigDecimal shippingFee;

  @Field(name = "subtotal", targetType = FieldType.DECIMAL128)
  private BigDecimal subTotal;

  @Field(name = "discount", targetType = FieldType.DECIMAL128)
  private BigDecimal discount;

  @Field(name = "total", targetType = FieldType.DECIMAL128)
  private BigDecimal total;

  private Currency currency;

  public OrderMongoEntity() {}

  public OrderMongoEntity(
    final String id, 
    final Boolean active, 
    final Instant createdAt, 
    final Instant updatedAt, 
    final OrderStatus status,
    final Set<OrderItemMongoEntity> items, 
    final String customerId, 
    final BigDecimal shippingFee, 
    final BigDecimal subTotal, 
    final BigDecimal discount, 
    final BigDecimal total, 
    final Currency currency
  ) {
    this.id = id;
    this.active = active;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.status = status;
    this.items = items;
    this.customerId = customerId;
    this.shippingFee = shippingFee;
    this.subTotal = subTotal;
    this.discount = discount;
    this.total = total;
    this.currency = currency;
  }

  public static Order toAggregate(final OrderMongoEntity entity) {
    final var order =  new Order(
      new OrderID(entity.id),
      entity.status,
      OrderItemMongoEntity.toAggregateSet(entity.items),
      entity.customerId,
      entity.shippingFee,
      entity.discount,
      entity.currency
    );
    order.setSubTotal(entity.subTotal);
    order.setTotal(entity.total);
    return order;
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
      order.getShippingFee(),
      order.getSubTotal(),
      order.getDiscount(),
      order.getTotal(),
      order.getCurrency()
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
