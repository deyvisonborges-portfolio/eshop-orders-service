package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.entities;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderItem;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderItemID;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "order_items")
public class OrderItemMongoEntity {
  @MongoId
  @Indexed(name = "id")
  private String id;  

  private Boolean active;

  @Field(name = "created_at")
  private Instant createdAt;

  @Field(name = "updated_at")
  private Instant updatedAt;

  @Field(name = "product_id")
  private String productId;

  @Field(name = "price_amount", targetType = FieldType.DECIMAL128)
  private BigDecimal priceAmount;

  @Field(name = "price_currency")
  private String priceCurrency;

  private int quantity;

  public OrderItemMongoEntity() {}

  public OrderItemMongoEntity(
    final String id, 
    final Boolean active, 
    final Instant createdAt, 
    final Instant updatedAt, 
    final String productId,
    final BigDecimal priceAmount, 
    final String priceCurrency, 
    final int quantity
  ) {
    this.id = id;
    this.active = active;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.productId = productId;
    this.priceAmount = priceAmount;
    this.priceCurrency = priceCurrency;
    this.quantity = quantity;
  }

  public static OrderItemMongoEntity toMongoEntity(final OrderItem orderItem) {
    return new OrderItemMongoEntity(
      orderItem.getId().getValue(),
      orderItem.getActive(),
      orderItem.getCreatedAt(),
      orderItem.getUpdatedAt(),
      orderItem.getProductId(),
      orderItem.getPrice().getAmount(),
      orderItem.getPrice().getCurrency(),
      orderItem.getQuantity());
  }

  public static OrderItem toAggregate(final OrderItemMongoEntity entity) {
    return new OrderItem(
      new OrderItemID(entity.id),
      entity.productId,
      entity.quantity,
      new Money(entity.priceAmount, entity.priceCurrency)
    );
  }

  public static Set<OrderItem> toAggregateSet(final Set<OrderItemMongoEntity> items) {
    return items.stream()
      .map(OrderItemMongoEntity::toAggregate)
      .collect(Collectors.toSet());
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
}
