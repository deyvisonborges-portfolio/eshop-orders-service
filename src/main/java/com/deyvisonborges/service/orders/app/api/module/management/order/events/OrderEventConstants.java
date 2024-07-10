package com.deyvisonborges.service.orders.app.api.module.management.order.events;

public final class OrderEventConstants {
  public static final String ORDER_EXCHANGE_NAME = "order.direct";
  public static final String ORDER_DLX_EXCHANGE = "order.dlx.direct";
  public static final String ORDER_CREATION_QUEUE_NAME = "order.v1.creation-queue";
  public static final String ORDER_CREATION_COMPENSATION_QUEUE = "order.v1.creation-compensation-queue";
  public static final String ORDER_CREATION_EVENT_ROUTING_KEY = "order.v1.event.order-created";
  public static final String ORDER_DQL_QUEUE = "order.dlq.queue";
}
