package com.deyvisonborges.service.orders.app.messaging.artifacts.events.listeners;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderRepository;
import com.deyvisonborges.service.orders.app.messaging.artifacts.events.OrderEventConstants;
import com.deyvisonborges.service.orders.app.messaging.artifacts.events.OrderEventMessage;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;

@Component
public class UpsertOrderEventListener {
  private final OrderRepository orderRepository;

  public UpsertOrderEventListener(final OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @RabbitListener(bindings = @QueueBinding(
    value = @Queue(value = OrderEventConstants.ORDER_QUEUE_NAME),
    exchange = @Exchange(value = OrderEventConstants.ORDER_EXCHANGE_NAME, type = "direct"),
    key = {
      OrderEventConstants.ORDER_CREATED_EVENT_ROUTING_KEY,
      OrderEventConstants.ORDER_UPDATED_EVENT_ROUTING_KEY
    }
  ))
  public void onMessage(final OrderEventMessage message) {
    Order order = orderRepository.findById(message.getOrderId()).get();
    order.setStatus(
      message.getStatus() == OrderStatus.CREATED.getValue() 
        ? OrderStatus.CREATED 
        :  OrderStatus.UPDATED
    );
    orderRepository.save(order);
  }
}
