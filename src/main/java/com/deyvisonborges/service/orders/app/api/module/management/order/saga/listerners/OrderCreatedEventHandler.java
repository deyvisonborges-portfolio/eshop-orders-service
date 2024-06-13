package com.deyvisonborges.service.orders.app.api.module.management.order.saga.listerners;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderReadableRepository;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderWritableRepository;
import com.deyvisonborges.service.orders.app.api.module.management.order.saga.CreateOrderOrchestratorService;
import com.deyvisonborges.service.orders.app.exception.NotFoundException;
import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventConstants;
import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventMessage;

@Component
public class OrderCreatedEventHandler {
  private final OrderReadableRepository orderReadableService;
  private final OrderWritableRepository orderWritableRepository;
  private final CreateOrderOrchestratorService createOrderOrchestratorService;

  public OrderCreatedEventHandler(
    final OrderReadableRepository orderReadableRepository,
    final OrderWritableRepository orderWritableRepository,
    final CreateOrderOrchestratorService createOrderOrchestratorService
  ) {
    this.orderReadableService = orderReadableRepository;
    this.orderWritableRepository = orderWritableRepository;
    this.createOrderOrchestratorService = createOrderOrchestratorService;
  }

  @RabbitListener(bindings = @QueueBinding(
    value = @Queue(value = OrderEventConstants.ORDER_QUEUE_UPSERT_NAME),
    exchange = @Exchange(value = OrderEventConstants.ORDER_EXCHANGE_NAME, type = "direct"),
    key = {
      OrderEventConstants.ORDER_CREATED_EVENT_ROUTING_KEY,
      OrderEventConstants.ORDER_UPDATED_EVENT_ROUTING_KEY
    }
  ))
  public void onOrderCreated(final OrderEventMessage orderEventMessage) {
    try {
      final var orderId = orderEventMessage.getOrderId();
      final var order = this.orderWritableRepository.findById(orderId)
        .orElseThrow(() -> new NotFoundException("Order not found in writable repository"));
      this.orderReadableService.save(order);
    } catch (Exception e) {
      this.createOrderOrchestratorService.onOrderCreationFailed(orderEventMessage);
    }
  }
}