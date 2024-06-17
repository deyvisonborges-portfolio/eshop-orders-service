// package com.deyvisonborges.service.orders.app.messaging.events.order.listeners;

// import org.springframework.amqp.rabbit.annotation.Exchange;
// import org.springframework.amqp.rabbit.annotation.Queue;
// import org.springframework.amqp.rabbit.annotation.QueueBinding;
// import org.springframework.amqp.rabbit.annotation.RabbitListener;
// import org.springframework.stereotype.Component;
// import org.springframework.transaction.annotation.Transactional;

// import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderReadableRepository;
// import com.deyvisonborges.service.orders.app.messaging.client.rabbitmq.DlqPublisher;
// import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventConstants;
// import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventMessage;

// @Component
// @Transactional
// public class UpsertOrderEventListener {
//   private final OrderReadableRepository orderReadableService;
//   private final DlqPublisher dlqPublisher;

//   public UpsertOrderEventListener(
//       final OrderReadableRepository orderReadableService,
//       final DlqPublisher dlqPublisher
//     ) {
//     this.orderReadableService = orderReadableService;
//     this.dlqPublisher = dlqPublisher;
//   }

//   @RabbitListener(bindings = @QueueBinding(
//       value = @Queue(value = OrderEventConstants.ORDER_QUEUE_UPSERT_NAME), 
//       exchange = @Exchange(value = OrderEventConstants.ORDER_EXCHANGE_NAME, type = "direct"), key = {
//       OrderEventConstants.ORDER_CREATED_EVENT_ROUTING_KEY,
//       OrderEventConstants.ORDER_UPDATED_EVENT_ROUTING_KEY
//   }))
//   public void onMessage(final OrderEventMessage message) {
//     try {
//       final var order = this.orderReadableService.findById(message.getOrderId());

//       if(order.isPresent()) return;

//       this.orderReadableService.save(order.get());
//     } catch (Exception e) {
//       e.printStackTrace();
//       dlqPublisher.publishToDlq(
//         OrderEventConstants.ORDER_DLX_EXCHANGE,
//         OrderEventConstants.ORDER_DQL_QUEUE,
//         message
//       );
//     }
//   }

// }
