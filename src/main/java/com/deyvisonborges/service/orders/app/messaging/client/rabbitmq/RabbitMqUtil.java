package com.deyvisonborges.service.orders.app.messaging.client.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Queue;

public class RabbitMqUtil {
  public static Queue createQueueWithDLQ(String queueName, String dlxExchange, String dlqRoutingKey) {
    Map<String, Object> args = new HashMap<>();
    args.put("x-dead-letter-exchange", dlxExchange);
    args.put("x-dead-letter-routing-key", dlqRoutingKey);

    return new Queue(queueName, true, false, false, args);
  }
}
