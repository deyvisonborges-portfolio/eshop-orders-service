package com.deyvisonborges.service.orders.app.api.module.management.order.events;

public final class OrderEventConstants {
  // Exchange definitions
  public static final String ORDER_EXCHANGE_NAME = "order.direct";
  public static final String ORDER_DLX_EXCHANGE = "order.dlx.direct";

  // Upsert queue (for initial order creation)
  public static final String ORDER_CREATION_QUEUE_NAME = "order.v1.creation-queue";

  // Compensation queues and bindings
  public static final String ORDER_CREATION_COMPENSATION_QUEUE = "order.v1.creation-compensation-queue";
  public static final String ORDER_CREATION_COMPENSATION_ROUTING_KEY = "order.v1.event.creation-compensation";

  // Routing keys for specific events
  public static final String ORDER_CREATION_EVENT_ROUTING_KEY = "order.v1.event.order-created";
  public static final String ORDER_UPDATED_EVENT_ROUTING_KEY = "order.v1.event.order-updated";

  // Dead letter queue
  public static final String ORDER_DQL_QUEUE = "order.dlq.queue";
}
