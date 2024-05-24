package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
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

  // @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
  // private Set<OrderPaymentJPAEntity> payments = new HashSet<>();

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

  // public Set<OrderPaymentJPAEntity> getPayments() {
  //   return payments;
  // }

  // public void setPayments(Set<OrderPaymentJPAEntity> payments) {
  //   this.payments = payments;
  // }

  public BigDecimal getShippingFeeAmount() {
    return shippingFeeAmount;
  }

  public void setShippingFeeAmount(BigDecimal shippingFeeAmount) {
    this.shippingFeeAmount = shippingFeeAmount;
  }

  public String getShippingFeeCurrency() {
    return shippingFeeCurrency;
  }

  public void setShippingFeeCurrency(String shippingFeeCurrency) {
    this.shippingFeeCurrency = shippingFeeCurrency;
  }

  public BigDecimal getSubTotalAmount() {
    return subTotalAmount;
  }

  public void setSubTotalAmount(BigDecimal subTotalAmount) {
    this.subTotalAmount = subTotalAmount;
  }

  public String getSubTotalCurrency() {
    return subTotalCurrency;
  }

  public void setSubTotalCurrency(String subTotalCurrency) {
    this.subTotalCurrency = subTotalCurrency;
  }

  public BigDecimal getDiscountAmount() {
    return discountAmount;
  }

  public void setDiscountAmount(BigDecimal discountAmount) {
    this.discountAmount = discountAmount;
  }

  public String getDiscountCurrency() {
    return discountCurrency;
  }

  public void setDiscountCurrency(String discountCurrency) {
    this.discountCurrency = discountCurrency;
  }

  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  public String getTotalCurrency() {
    return totalCurrency;
  }

  public void setTotalCurrency(String totalCurrency) {
    this.totalCurrency = totalCurrency;
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
    // final Set<OrderPaymentJPAEntity> payments,
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
    // this.payments = payments;
    this.subTotalAmount = subTotalAmount;
    this.subTotalCurrency = subTotalCurrency;
    this.shippingFeeAmount = shippingFeeAmount;
    this.shippingFeeCurrency = shippingFeeCurrency;
    this.discountAmount = discountAmount;
    this.discountCurrency = discountCurrency;
    this.totalAmount = totalAmount;
    this.totalCurrency = totalCurrency;
  }

  /**
   * Converte a entidade JPA para a entidade de dominio
   */
  public static OrderJPAEntity toJPAEntity(final Order order) {
    // Converte o Domain Entity (OrderItem) para o JPA Entity (OrderItemJPAEntity)
    Set<OrderItemJPAEntity> orderItems = order.getItems().stream()
      .map(OrderItemJPAEntity::toJPAEntity)
      .collect(Collectors.toSet());
      
    // // Converte o Domain Composition Entity (Set<String>) para o JPA Entity (OrderPaymentJPAEntity)
    // Set<OrderPaymentJPAEntity> payments = order.getPaymentsIds().stream()
    //   .map(OrderPaymentJPAEntity::from)
    //   .collect(Collectors.toSet());

    // Gera o objeto OrderJPA
    final var orderJpa = new OrderJPAEntity(
      order.getId().getValue(),
      order.getActive(),
      order.getCreatedAt(),
      order.getUpdatedAt(),
      order.getStatus(),
      orderItems,
      order.getCustomerId(),
      // payments,
      order.getSubTotal().getAmount(),
      order.getSubTotal().getCurrency(),
      order.getShippingFee().getAmount(),
      order.getShippingFee().getCurrency(),
      order.getDiscount().getAmount(),
      order.getDiscount().getCurrency(),
      order.getTotal().getAmount(),
      order.getTotal().getCurrency()
    );

    // Insere o id do pedido para cada item de pedido
    orderItems.forEach(item -> item.setOrder(orderJpa));

    // // Insere o id do pedido para cada pagamento
    // payments.forEach(payment -> payment.setOrder(orderJpa));

    return orderJpa;
  }

  /**
   * Converte a entidade de domínio para entidade JPA
   */
  public static Order toAggregate(final OrderJPAEntity entity) {
    // Converte o JPA Entity (OrderItemJPAEntity) para o Domain Entity (OrderItem) 
    Set<OrderItem> orderItems = entity.items.stream()
      .map((orderItem) -> {
        return OrderItemJPAEntity.toAggregate(orderItem);
      })
      .collect(Collectors.toSet());

    // // Converte o JPA Entity (OrderPaymentJPAEntity) para o Domain Composition Entity (Set<String>)
    // Set<String> paymentIds = entity.payments.stream()
    //   .map(payment -> payment.getId().toString())
    //   .collect(Collectors.toSet());

    // Gera o objeto OrderJPA
    final var order = new Order(
      new OrderID(entity.id),
      entity.status,
      orderItems,
      entity.customerId,
      null,
      new Money(entity.subTotalAmount, entity.subTotalCurrency),
      new Money(entity.shippingFeeAmount, entity.shippingFeeCurrency),
      new Money(entity.discountAmount, entity.discountCurrency),
      new Money(entity.totalAmount, entity.totalCurrency)
    );
    return order;
  }
}
