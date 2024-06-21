package com.deyvisonborges.service.orders.app.api.module.management.order.events.order.publishers;
// package com.deyvisonborges.service.orders.app.messaging.events.order.publishers;

// import org.springframework.stereotype.Component;

// import com.deyvisonborges.service.orders.app.messaging.client.rabbitmq.RabbitmqEventEmitter;
// import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEvent;
// import com.deyvisonborges.service.orders.app.messaging.events.order.OrderEventConstants;
// import com.deyvisonborges.service.orders.core.domain.EventEmitter;

// @Component
// public class CreateOrderEventPublisher extends EventEmitter<OrderEvent> {
//   private final RabbitmqEventEmitter emitter;

//   public CreateOrderEventPublisher(final RabbitmqEventEmitter emitter) {
//     this.emitter = emitter;
//   } 

//   @Override
//   public void applyOn(final OrderEvent command) {
//     this.emitter.emit(
//       OrderEventConstants.ORDER_EXCHANGE_NAME,
//       OrderEventConstants.ORDER_CREATED_EVENT_ROUTING_KEY,
//       command
//     );
//   }
// }
