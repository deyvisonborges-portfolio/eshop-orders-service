package com.deyvisonborges.service.orders.app.api.module.management.order.events.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEventConstants;
import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEventMessage;
import com.deyvisonborges.service.orders.app.api.module.management.order.usecase.deleteorder.DeleteOrderCommandHandler;

@Service
public class OrderCreationCompensationEventListener {
  private final DeleteOrderCommandHandler deleteOrderCommandHandler;

  public OrderCreationCompensationEventListener(final DeleteOrderCommandHandler deleteOrderCommandHandler) {
    this.deleteOrderCommandHandler = deleteOrderCommandHandler;
  }

  @RabbitListener(queues = OrderEventConstants.ORDER_CREATION_COMPENSATION_QUEUE)
  public void handle(OrderEventMessage orderEventMessage) {
    String orderId = orderEventMessage.getOrderId();
    deleteOrderCommandHandler.handle(orderId);
  }
}
