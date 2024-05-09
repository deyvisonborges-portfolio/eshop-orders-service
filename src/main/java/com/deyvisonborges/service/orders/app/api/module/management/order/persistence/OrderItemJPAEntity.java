package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name = "order_items")
public class OrderItemJPAEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private UUID id;

  private Boolean active;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;

  @Column(name = "product_id", nullable = false)
  private String productId;

  @Column(name = "price_amount", nullable = false)
  private BigDecimal priceAmount;

  @Column(name = "price_currency", nullable = false)
  private String priceCurrency;

  @Column(nullable = false)
  private int quantity;

  @ManyToOne
  @JoinColumn(name = "order_id", nullable = false)
  private OrderJPAEntity order;

  public UUID getId() {
    return id;
  }

  public Boolean getActive() {
    return active;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public String getProductId() {
    return productId;
  }

  public BigDecimal getPriceAmount() {
    return priceAmount;
  }

  public String getPriceCurrency() {
    return priceCurrency;
  }

  public int getQuantity() {
    return quantity;
  }

  public OrderJPAEntity getOrder() {
    return order;
  }

  public OrderItemJPAEntity() {}

  public OrderItemJPAEntity(
    final UUID id, 
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

  public static OrderItemJPAEntity toJPAEntity(final OrderItem orderItem) {
    return new OrderItemJPAEntity(
      UUID.fromString(orderItem.getId().getValue()),
      orderItem.getActive(),
      orderItem.getCreatedAt(),
      orderItem.getUpdatedAt(),
      orderItem.getProductId(),
      orderItem.getPrice().getAmount(),
      orderItem.getPrice().getCurrency(),
      orderItem.getQuantity());
  }

  public static OrderItem toAggregate(final OrderItemJPAEntity entity) {
    return new OrderItem(
      entity.productId,
      entity.quantity,
      new Money(entity.priceAmount, entity.priceCurrency)
    );
  }

  /**
   * Setters
   */
  public void setOrder(OrderJPAEntity order) {
    this.order = order;
  }
}
