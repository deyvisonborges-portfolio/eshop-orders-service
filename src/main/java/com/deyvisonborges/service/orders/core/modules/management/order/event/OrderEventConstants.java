package com.deyvisonborges.service.orders.core.modules.management.order.event;

public final class OrderEventConstants {
  public static final String ORDER_EXCHANGE_NAME = "order.v1.direct";
  public static final String ORDER_QUEUE_NAME = "order.v1.direct";
  public static final String ORDER_HISTORY_QUEUE_NAME = "order.v1.queue.history";
  public static final String ORDER_CREATED_EVENT_ROUTING_KEY = "order.v1.event.order-created";
}