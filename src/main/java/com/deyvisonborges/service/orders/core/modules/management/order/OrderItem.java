package com.deyvisonborges.service.orders.core.modules.management.order;

import java.time.Instant;
import java.util.UUID;

import com.deyvisonborges.service.orders.core.domain.Entity;
import com.deyvisonborges.service.orders.core.domain.primitives.Money;

public class OrderItem extends Entity<OrderItemID> {
  private String productId;
  private int quantity;
  private Money price;

  protected OrderItem(
    final String productId,
    final int quantity,
    final Money price
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

  public static OrderItem factory(
    final String id,
    final Boolean active,
    final Instant createdAt,
    final Instant updatedAt,
    final String productId,
    final int quantity,
    final Money price
  ) {
    final var orderItem = new OrderItem(productId, quantity, price);
    orderItem.setId(OrderItemID.from(OrderItemID.class, UUID.fromString(id)));
    orderItem.setActive(active);
    orderItem.setCreatedAt(createdAt);
    orderItem.setUpdatedAt(updatedAt);
    return orderItem;
  }

  public String getProductId() {
    return this.productId;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public Money getPrice() {
    return this.price;
  }

  public void setProductId(final String productId) {
    this.productId = productId;
  }

  public void setQuantity(final int quantity) {
    this.quantity = quantity;
  }

  public void setMoney(final Money money) {
    this.price = money;
  }
  
  public void setId(final OrderItemID id) {
    this.id = id;
  }

  public void setActive(final Boolean active) {
    this.active = active;
  }

  public void setCreatedAt(final Instant createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(final Instant updatedAt) {
    this.updatedAt = updatedAt;
  }
}
