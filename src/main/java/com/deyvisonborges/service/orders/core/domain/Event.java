package com.deyvisonborges.service.orders.core.domain;

import java.time.Instant;
import java.util.UUID;

public abstract class Event<T> {
  private String id = UUID.randomUUID().toString();
  private int version = 1;
  private String title;
  private Instant start;
  private Instant end;

  protected abstract void applyOn(T aggregate);

  protected Event() {
    if (end.isBefore(start)) {
      throw new IllegalArgumentException("End date must be after start date");
    }
  }

  public String getId() {
    return id;
  }

  public int getVersion() {
    return version;
  }

  public String getTitle() {
    return title;
  }

  public Instant getStart() {
    return start;
  }

  public Instant getEnd() {
    return end;
  }
}