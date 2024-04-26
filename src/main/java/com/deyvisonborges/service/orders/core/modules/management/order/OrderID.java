package com.deyvisonborges.service.orders.core.modules.management.order;

import java.util.Objects;
import java.util.UUID;

public class OrderID {
  private final String value;

  public OrderID(final String value) {
    Objects.requireNonNull(value);
    this.value = validate(value);
  }

  public String getValue() {
    return this.value;
  }

  public static String generate() {
    return UUID.randomUUID().toString();
  }
  
  private String validate(final String value) {
    try {
      UUID.fromString(value);
      return value;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid OrderID format");
    }
  }
}