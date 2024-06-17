package com.deyvisonborges.service.orders.app.messaging.events.order;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventConstants;
import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventMessage;

@Component
public class DlqListener {
  @RabbitListener(queues = OrderEventConstants.ORDER_DQL_QUEUE)
  public void onDlqMessage(final OrderEventMessage message) {
    // Handle the dead-lettered message, log it or take corrective action
    System.out.println("Received message in DLQ: " + message.getOrderId());
  }
}
