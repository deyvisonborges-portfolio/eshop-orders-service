package com.deyvisonborges.service.orders.core.domain;

import java.time.Instant;
// import java.util.ArrayList;
// import java.util.List;

public abstract class AggregateRoot<ID extends Identifier<ID>> extends Entity<ID> {
  // @SuppressWarnings("rawtypes")
  // private final List<Event> changes = new ArrayList<>();
  // private int version = -1;

  protected AggregateRoot(
      final ID id,
      final Boolean active,
      final Instant createdAt,
      final Instant updatedAt) {
    super(id, active, createdAt, updatedAt);
  }

  // @SuppressWarnings("unchecked")
  // protected void loadsFromHistory(
  //   @SuppressWarnings("rawtypes") final List<Event> history
  // ) {
  //   history.forEach(e -> {
  //     e.applyOn(this);
  //     changes.add(e);
  //     version += 1;
  //   });
  // }

  // @SuppressWarnings({ "unchecked", "rawtypes" })
  // protected void applyChanges(final Event event) {
  //   event.applyOn(this);
  //   changes.add(event);
  // }

  // @SuppressWarnings("rawtypes")
  // public List<Event> getChanges() {
  //   return this.changes;
  // }

  // public int getVersion() {
  //   return this.version;
  // }
}