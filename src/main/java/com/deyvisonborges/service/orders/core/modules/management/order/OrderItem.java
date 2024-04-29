package com.deyvisonborges.service.orders.core.modules.management.order;

import java.time.Instant;

import com.deyvisonborges.service.orders.core.Entity;
import com.deyvisonborges.service.orders.core.primitives.Money;

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

  public String getProductId() {
    return this.productId;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public Money price() {
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
}
