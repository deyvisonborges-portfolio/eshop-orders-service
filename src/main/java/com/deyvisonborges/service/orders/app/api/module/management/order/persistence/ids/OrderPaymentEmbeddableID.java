package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.ids;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderPaymentEmbeddableID {
  @Column
  private String orderId;

  @Column
  private String paymentId;

  public OrderPaymentEmbeddableID() {}

  public OrderPaymentEmbeddableID(final String orderId, final String paymentId) {
    this.orderId = orderId;
    this.paymentId = paymentId;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getPaymentId() {
    return paymentId;
  }
}