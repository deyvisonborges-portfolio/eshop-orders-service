package com.deyvisonborges.service.orders.core.domain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.deyvisonborges.service.orders.core.domain.Event;

public class InMemoryEventStore implements EventStoreContract {
  @SuppressWarnings("rawtypes")
  private Map<String, List<Event>> events = new ConcurrentHashMap<>();

  @SuppressWarnings("rawtypes")
  @Override
  public List<Event> getEventsForAggregate(String aggregateId) {
    return this.events.get(aggregateId);
  }

  @Override
  public void saveEvents(String aggregateId, long version, @SuppressWarnings("rawtypes") List<Event> events) {
    this.events.computeIfAbsent(aggregateId, id -> new ArrayList<>()).addAll(events);
  }
}
