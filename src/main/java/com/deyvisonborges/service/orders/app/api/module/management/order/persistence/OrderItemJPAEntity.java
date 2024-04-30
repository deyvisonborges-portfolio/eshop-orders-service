package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderItem;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_items")
public class OrderItemJPAEntity implements Serializable {
	/*
	* Default Attributes
	* */
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
	* */
	@Column(name = "product_id", nullable = false)
	private String productId;

	@Column(name = "price_amount", nullable = false)
	private BigDecimal priceAmount;

  @Column(name = "price_currency", nullable = false)
  private String priceCurrency;

	@Column(nullable = false)
	private int quantity;

	public OrderItemJPAEntity() {
	}

	public OrderItemJPAEntity(
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

  public static OrderItemJPAEntity from(final OrderItem orderItem) {
    return new OrderItemJPAEntity(
      orderItem.getId().getValue().toString(),
      orderItem.getActive(),
      orderItem.getCreatedAt(),
      orderItem.getUpdatedAt(),
      orderItem.getProductId(),
      orderItem.getPrice().getAmount(),
      orderItem.getPrice().getCurrency(),
      orderItem.getQuantity()
    );
  }
  
  public static OrderItem toAggregate(final OrderItemJPAEntity entity) {
    return OrderItem.factory(
      entity.id, 
      entity.active, 
      entity.createdAt, 
      entity.updatedAt, 
      entity.productId, 
      entity.quantity, 
      new Money(entity.priceAmount, entity.priceCurrency)
    );
  }
}