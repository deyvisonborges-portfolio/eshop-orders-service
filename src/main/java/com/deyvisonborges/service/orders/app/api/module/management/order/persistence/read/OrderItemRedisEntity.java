package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

@RedisHash("order_items")
public class OrderItemRedisEntity {
  @Id
  @Column(nullable = false)
  private String id;

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

  public OrderItemRedisEntity() {}

  public OrderItemRedisEntity(
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

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public BigDecimal getPriceAmount() {
    return priceAmount;
  }

  public void setPriceAmount(BigDecimal priceAmount) {
    this.priceAmount = priceAmount;
  }

  public String getPriceCurrency() {
    return priceCurrency;
  }

  public void setPriceCurrency(String priceCurrency) {
    this.priceCurrency = priceCurrency;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
