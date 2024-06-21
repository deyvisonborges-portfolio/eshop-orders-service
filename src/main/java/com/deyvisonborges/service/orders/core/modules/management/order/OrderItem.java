package com.deyvisonborges.service.orders.core.modules.management.order;

import java.math.BigDecimal;
import java.time.Instant;

import com.deyvisonborges.service.orders.core.domain.Entity;

public class OrderItem extends Entity<OrderItemID> {
  private String productId;
  private int quantity;
  private BigDecimal price;

  public OrderItem(
    final String productId,
    final int quantity,
    final BigDecimal price
  ) {
    super(
      OrderItemID.generate(OrderItemID.class), 
      true, 
      Instant.now(), 
      Instant.now()
    );

    this.productId = productId;
    this.quantity = quantity;
    this.price = price;
  }

  public OrderItem(
    final OrderItemID id,
    final String productId,
    final int quantity,
    final BigDecimal price
  ) {
    super(
      new OrderItemID(id.getValue()), 
      true, 
      Instant.now(), 
      Instant.now()
    );

    this.productId = productId;
    this.quantity = quantity;
    this.price = price;
  }

  @Override
  public OrderItemID getId() {
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

  public String getProductId() {
    return this.productId;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public BigDecimal getPrice() {
    return this.price;
  }
  
  public void setId(final OrderItemID id) {
    this.id = id;
  }

  public void setUpdatedAt(final Instant value) {
    this.updatedAt = value;
  }
}
