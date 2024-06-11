package com.deyvisonborges.service.orders.app.messaging.events.order.listeners;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderReadableRepository;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderWritableRepository;
import com.deyvisonborges.service.orders.app.messaging.client.rabbitmq.DlqPublisher;
import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventConstants;
import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventMessage;

@Component
@Transactional
public class UpsertOrderEventListener {
  private final OrderReadableRepository orderReadableService;
  private final OrderWritableRepository orderWritableService;
  private final DlqPublisher dlqPublisher;

  public UpsertOrderEventListener(
      final OrderReadableRepository orderReadableService,
      final OrderWritableRepository orderWritableService,
      final DlqPublisher dlqPublisher
    ) {
    this.orderReadableService = orderReadableService;
    this.orderWritableService = orderWritableService;
    this.dlqPublisher = dlqPublisher;
  }

  @RabbitListener(bindings = @QueueBinding(value = @Queue(value = OrderEventConstants.ORDER_QUEUE_UPSERT_NAME), exchange = @Exchange(value = OrderEventConstants.ORDER_EXCHANGE_NAME, type = "direct"), key = {
      OrderEventConstants.ORDER_CREATED_EVENT_ROUTING_KEY,
      OrderEventConstants.ORDER_UPDATED_EVENT_ROUTING_KEY
  }))
  public void onMessage(final OrderEventMessage message) {
    try {
      final var order = this.orderWritableService.findById(message.getOrderId()).get();
      this.orderReadableService.save(order);
    } catch (Exception e) {
      // Logging the exception for debugging purposes
      e.printStackTrace();
            
      // Publish the message to DLQ
      dlqPublisher.publishToDlq(
        OrderEventConstants.ORDER_DLX_EXCHANGE,
        OrderEventConstants.ORDER_DQL_QUEUE,
        message // Pass the OrderEventMessage directly to the publisher
      );
      // dlqPublisher.publishToDlq(
      //   OrderEventConstants.ORDER_DLX_EXCHANGE,
      //   OrderEventConstants.ORDER_DQL_QUEUE,
      //   new OrderEventMessage(
      //     message.getId(), 
      //     message.getVersion(), 
      //     message.getTitle(), 
      //     message.getStart(), null, null, null
      //   )
      // );
      // // Logging the exception for debugging purposes
      // e.printStackTrace();

      // // Check if the exception is due to order already existing
      // if (e.getCause() instanceof DataIntegrityViolationException) {
      //   // Log the error and reject the message silently
      //   System.out.println("Order already exists. Rejecting the message silently.");
      //   return; // Exiting method without throwing exception
      // }

      // // Re-throwing the exception to trigger DLQ for other cases
      // throw new RuntimeException("Error processing message: " + e.getMessage(), e);
    }
  }

}
