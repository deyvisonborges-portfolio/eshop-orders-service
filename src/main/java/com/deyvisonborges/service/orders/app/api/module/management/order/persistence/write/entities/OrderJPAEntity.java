package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.write.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.deyvisonborges.service.orders.core.modules.management.order.Currency;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderID;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderItem;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "Order")
@Table(name = "orders")
public class OrderJPAEntity implements Serializable {
  @Id
  @Column(nullable = false)
  private String id;

  private Boolean active;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private OrderStatus status;

  @OneToMany(mappedBy = "order",orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<OrderItemJPAEntity> items = new HashSet<>();

  @Column(name = "customer_id", nullable = false)
  private String customerId;

  @Column(name = "shipping_fee", nullable = false)
  private BigDecimal shippingFee;

  @Column(name = "subtotal", nullable = false)
  private BigDecimal subTotal;

  @Column(name = "discount", nullable = false)
  private BigDecimal discount;

  @Column(name = "total", nullable = false)
  private BigDecimal total;

  private Currency currency;

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

  public Set<OrderItemJPAEntity> getItems() {
    return items;
  }

  public void setItems(Set<OrderItemJPAEntity> items) {
    this.items = items;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public Currency getCurrency() {
    return this.currency;
  }

  public void setCurrency(Currency currency) {
    this.currency = currency;
  }

  public OrderJPAEntity() {}

  public OrderJPAEntity(
    final String id,
    final Boolean active,
    final Instant createdAt,
    final Instant updatedAt,
    final OrderStatus status,
    final Set<OrderItemJPAEntity> items,
    final String customerId,
    final BigDecimal subTotal,
    final BigDecimal shippingFee,
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
    this.subTotal = subTotal;
    this.shippingFee = shippingFee;
    this.discount = discount;
    this.total = total;
    this.currency = currency;
  }

  public static OrderJPAEntity toJPAEntity(final Order order) {
    Set<OrderItemJPAEntity> orderItems = order.getItems().stream()
      .map(OrderItemJPAEntity::toJPAEntity)
      .collect(Collectors.toSet());

    final var orderJpa = new OrderJPAEntity(
      order.getId().getValue(),
      order.getActive(),
      order.getCreatedAt(),
      order.getUpdatedAt(),
      order.getStatus(),
      orderItems,
      order.getCustomerId(),
      order.getSubTotal(),
      order.getShippingFee(),
      order.getDiscount(),
      order.getTotal(),
      order.getCurrency()
    );

    orderItems.forEach(item -> item.setOrder(orderJpa));
    return orderJpa;
  }

  public static Order toAggregate(final OrderJPAEntity entity) {
    Set<OrderItem> orderItems = entity.items.stream()
      .map(OrderItemJPAEntity::toAggregate)
      .collect(Collectors.toSet());

    final var order = new Order(
      new OrderID(entity.id),
      entity.status,
      orderItems,
      entity.customerId,
      entity.shippingFee,
      entity.discount,
      entity.currency
    );
    order.setSubTotal(entity.subTotal);
    order.setTotal(entity.total);
    return order;
  }
}
