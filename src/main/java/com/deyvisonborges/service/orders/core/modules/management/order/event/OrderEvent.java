package com.deyvisonborges.service.orders.core.modules.management.order.event;

import java.time.Instant;

import com.deyvisonborges.service.orders.core.domain.EventMessage;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;

public class OrderEvent extends EventMessage {
  private String orderId;
  private OrderStatus status;
  
  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public static OrderEvent produce(final String orderId, final OrderStatus status){
    final var orderEvent = new OrderEvent();
    orderEvent.setOrderId(orderId);
    orderEvent.setStatus(status);
    orderEvent.setTitle("Order created");
    orderEvent.setStart(Instant.now());
    orderEvent.setEnd(Instant.now());
    return orderEvent;
  } 
}
