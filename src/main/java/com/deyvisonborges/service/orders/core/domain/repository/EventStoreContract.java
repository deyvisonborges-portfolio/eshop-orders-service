package com.deyvisonborges.service.orders.core.domain.repository;

import java.util.List;

import com.deyvisonborges.service.orders.core.domain.EventEmitter;

public interface EventStoreContract {
  @SuppressWarnings("rawtypes")
  List<EventEmitter> getEventsForAggregate(final String aggregateId);
  void saveEvents(final String aggregateId, final long version, @SuppressWarnings("rawtypes") final List<EventEmitter> events);
} 
