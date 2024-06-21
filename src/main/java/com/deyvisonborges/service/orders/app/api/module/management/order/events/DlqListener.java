package com.deyvisonborges.service.orders.app.api.module.management.order.events;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DlqListener {
  @RabbitListener(queues = OrderEventConstants.ORDER_DQL_QUEUE)
  public void onDlqMessage(final OrderEventMessage message) {
    System.out.println("Received message in DLQ: " + message.getOrderId());
  }
}
