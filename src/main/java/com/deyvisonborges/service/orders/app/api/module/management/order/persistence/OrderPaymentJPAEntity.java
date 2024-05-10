// package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

// import java.io.Serializable;
// import java.util.HashSet;
// import java.util.Set;

// import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.ids.OrderPaymentEmbeddableID;

// import jakarta.persistence.Column;
// import jakarta.persistence.EmbeddedId;
// import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;

// @Entity(name = "OrderPayment")
// @Table(name = "order_payments")
// public class OrderPaymentJPAEntity implements Serializable {
//   @EmbeddedId
//   private OrderPaymentEmbeddableID id;

//   @ManyToOne(fetch = FetchType.LAZY)
//   @JoinColumn(name = "order_id")
//   private OrderJPAEntity order;

//   public OrderPaymentJPAEntity() {}

//   public OrderPaymentJPAEntity(final OrderPaymentEmbeddableID id) {
//     this.id = id;
//   }

//   public static Set<String> toAggregate(final OrderPaymentJPAEntity entity) {
//     Set<String> idSet = new HashSet<>();
//     idSet.add(entity.getId().toString());
//     return idSet;
//   }

//   public static OrderPaymentJPAEntity from(final String paymentsId) {
//     return new OrderPaymentJPAEntity();
//   }

//   /*
//    * Getters
//    */
//   public String getId() {
//     return this.id.getOrderId();
//   }
  
//   public OrderJPAEntity getOrder() {
//     return order;
//   }
// }
