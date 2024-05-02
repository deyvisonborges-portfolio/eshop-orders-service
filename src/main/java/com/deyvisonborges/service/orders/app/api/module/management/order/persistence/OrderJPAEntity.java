package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderItem;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderJPAEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, unique = true, nullable = false)
  private String id;

  private Boolean active;

  @Column(name = "created_at")
  private Instant createdAt;

  @Column(name = "updated_at")
  private Instant updatedAt;

  /*
   * Required Attributes
   */
  @Column(nullable = false)
  private OrderStatus status;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
  private Set<OrderItemJPAEntity> items = new HashSet<>();

  @Column(name = "customer_id", nullable = false)
  private String customerId;

  @Column(name = "payments_ids", nullable = false)
  private Set<String> paymentsIds = new HashSet<>();
  
  @Column(name = "shipping_fee_amount", nullable = false)
  private BigDecimal shippingFeeAmount;
  
  @Column(name = "shipping_fee_currency", nullable = false)
  private String shippingFeeCurrency;

  @Column(name = "subtotal_amount", nullable = false)
  private BigDecimal subTotalAmount;

  @Column(name = "subtotal_currency", nullable = false)
  private String subTotalCurrency;

  @Column(name = "discount_amount", nullable = false)
  private BigDecimal discountAmount;

  @Column(name = "discount_currency", nullable = false)
  private String discountCurrency;

  @Column(name = "total_amount", nullable = false)
  private BigDecimal totalAmount;

  @Column(name = "total_currency", nullable = false)
  private String totalCurrency;

  public OrderJPAEntity() {
  }

  public OrderJPAEntity(
    final String id,
    final Boolean active,
    final Instant createdAt,
    final Instant updatedAt,
    final OrderStatus status,
    final Set<OrderItemJPAEntity> items,
    final String customerId,
    final Set<String> paymentsIds,
    final BigDecimal subTotalAmount,
    final String subTotalCurrency,
    final BigDecimal shippingFeeAmount,
    final String shippingFeeCurrency,
    final BigDecimal discountAmount,
    final String discountCurrency,
    final BigDecimal totalAmount,
    final String totalCurrency
  ) {
    this.id = id;
    this.active = active;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.status = status;
    this.items = items;
    this.customerId = customerId;
    this.paymentsIds = paymentsIds;
    this.subTotalAmount = subTotalAmount;
    this.subTotalCurrency = subTotalCurrency;
    this.shippingFeeAmount = shippingFeeAmount;
    this.shippingFeeCurrency = shippingFeeCurrency;
    this.discountAmount = discountAmount;
    this.discountCurrency = discountCurrency;
    this.totalAmount = totalAmount;
    this.totalCurrency = totalCurrency;
  }

  public static OrderJPAEntity from(final Order order) {
    Set<OrderItemJPAEntity> orderItems = order.getItems().stream()
      .map(OrderItemJPAEntity::from)
      .collect(Collectors.toSet());

    return new OrderJPAEntity(
      order.getId().getValue().toString(),
      order.getActive(),
      order.getCreatedAt(),
      order.getUpdatedAt(),
      order.getStatus(),
      orderItems,
      order.getCustomerId().toString(),
      order.getPaymentsIds(),
      order.getSubTotal().getAmount(),
      order.getSubTotal().getCurrency(),
      order.getShippingFee().getAmount(),
      order.getShippingFee().getCurrency(),
      order.getDiscount().getAmount(),
      order.getDiscount().getCurrency(),
      order.getTotal().getAmount(),
      order.getTotal().getCurrency()
    );
  }

  public static Order toAggregate(final OrderJPAEntity entity) {
    Set<OrderItem> orderItems = entity.items.stream()
      .map(OrderItemJPAEntity::toAggregate)
      .collect(Collectors.toSet());

    return Order.factory(
      entity.id,
      entity.active,
      entity.createdAt,
      entity.updatedAt,
      entity.status,
      orderItems,
      entity.customerId,
      entity.paymentsIds,
      new Money(entity.subTotalAmount, entity.subTotalCurrency),
      new Money(entity.shippingFeeAmount, entity.shippingFeeCurrency),
      new Money(entity.discountAmount, entity.discountCurrency),
      new Money(entity.totalAmount, entity.totalCurrency)
    );
  }
}
