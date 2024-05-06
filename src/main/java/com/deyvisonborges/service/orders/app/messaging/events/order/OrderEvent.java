package com.deyvisonborges.service.orders.app.messaging.events.order;

import java.time.Instant;
import java.util.UUID;

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

  public static OrderEvent fromOrderEventMessage(OrderEventMessage message) {
    OrderEvent event = new OrderEvent();
    event.setOrderId(message.getOrderId());
    event.setStatus(OrderStatus.valueOf(message.getStatus()));
    event.setTitle(message.getTitle());
    event.setStart(message.getStart());
    event.setEnd(message.getEnd());
    return event;
  }

  public static OrderEventMessage produce(final String orderId, final OrderStatus status) {
    OrderEventMessage eventMessage = new OrderEventMessage();
    eventMessage.setId(UUID.randomUUID().toString());
    eventMessage.setVersion(1);
    eventMessage.setTitle("Order created");
    eventMessage.setStart(Instant.now());
    eventMessage.setEnd(Instant.now());
    eventMessage.setOrderId(orderId);
    eventMessage.setStatus(status.toString());
    return eventMessage;
  }
}
