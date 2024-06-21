package com.deyvisonborges.service.orders.app.api.module.management.order.saga.listerners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEventConstants;
import com.deyvisonborges.service.orders.app.api.module.management.order.events.OrderEventMessage;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderWritableRepository;

@Component
public class OrderCompensationEventListener {
  private final OrderWritableRepository orderWritableRepository;

  public OrderCompensationEventListener(OrderWritableRepository orderWritableRepository) {
    this.orderWritableRepository = orderWritableRepository;
  }

  @RabbitListener(queues = OrderEventConstants.ORDER_COMPENSATION_QUEUE)
  public void handle(OrderEventMessage orderEventMessage) {
    String orderId = orderEventMessage.getOrderId();
    System.out.println("Fiz a compensação");
    orderWritableRepository.deleteById(orderId);
  }
}
