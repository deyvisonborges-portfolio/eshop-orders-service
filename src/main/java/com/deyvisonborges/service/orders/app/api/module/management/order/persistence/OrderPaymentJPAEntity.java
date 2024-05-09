package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "OrderPayment")
@Table(name = "order_payments")
public class OrderPaymentJPAEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private OrderJPAEntity order;

  public void setId(final UUID id) {
    this.id = id;
  }

  public OrderJPAEntity getOrder() {
    return order;
  }

  public OrderPaymentJPAEntity() {}

  public OrderPaymentJPAEntity(final UUID id) {
    this.id = id;
  }

  public static Set<String> toAggregate(final OrderPaymentJPAEntity entity) {
    Set<String> idSet = new HashSet<>();
    idSet.add(entity.getId().toString());
    return idSet;
  }

  public static OrderPaymentJPAEntity from(final String paymentsId) {
    return new OrderPaymentJPAEntity();
  }

  /*
   * Getters
   */
  public UUID getId() {
    return this.id;
  }

  /*
   * Setters
   */
  public void setOrder(OrderJPAEntity order) {
    this.order = order;
  }
}
