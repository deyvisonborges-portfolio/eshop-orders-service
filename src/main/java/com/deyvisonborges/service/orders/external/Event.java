package com.deyvisonborges.service.orders.external;

import java.time.Instant;

public record Event(Integer id, String title, Instant start, Instant end) {
  public Event {
    if (end.isBefore(start)) {
      throw new IllegalArgumentException("End date must be after start date");
    }
  }
}
