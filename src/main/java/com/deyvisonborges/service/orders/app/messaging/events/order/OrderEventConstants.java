package com.deyvisonborges.service.orders.app.messaging.events.order;

public final class OrderEventConstants {
  public static final String ORDER_EXCHANGE_NAME = "order.v1.direct";
  public static final String ORDER_QUEUE_UPSERT_NAME = "order.v1.queue-upsert";
  public static final String ORDER_QUEUE_CANCELLED_NAME = "order.v1.queue-cancelled";
  public static final String ORDER_HISTORY_QUEUE_NAME = "order.v1.queue.history";
  
  public static final String ORDER_CREATED_EVENT_ROUTING_KEY = "order.v1.event.order-created";
  public static final String ORDER_UPDATED_EVENT_ROUTING_KEY = "order.v1.event.order-updated";
  public static final String ORDER_CANCELLED_EVENT_ROUTING_KEY = "order.v1.event.order-cancelled";
  
  public static final String ORDER_DLX_NAME = "order.v1.dlx";
  public static final String ORDER_QUEUE_DQL_UPSERT_NAME = "order.v1.queue-dlq";
}
