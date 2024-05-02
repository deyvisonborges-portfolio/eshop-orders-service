package com.deyvisonborges.service.orders.app.messaging.client.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitmqEventEmitter {
  private final AmqpTemplate template;
  private final ObjectMapper mapper;

  public RabbitmqEventEmitter(AmqpTemplate template, ObjectMapper mapper) {
		this.template = template;
		this.mapper = mapper;
	}

	public void emit(final String queueName, final Object message) {
		var processedMessage = this.handleMessage(message);
		this.template.convertAndSend(queueName, processedMessage);
	}

  public void emit(final String exchange, final String routingKey, final Object message) {
		var processedMessage = this.handleMessage(message);
		this.template.convertAndSend(exchange, routingKey, processedMessage);
	}

	private String handleMessage(final Object message) {
		try {
			return new String(mapper.writeValueAsString(message));
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Fail to process message");
		}
	}
}
