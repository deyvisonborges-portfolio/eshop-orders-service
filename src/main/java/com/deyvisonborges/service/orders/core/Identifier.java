package com.deyvisonborges.service.orders.core;

import java.util.Objects;
import java.util.UUID;

public abstract class Identifier<T extends Identifier<T>> extends ValueObject {
  private final String value;

  protected Identifier(final String value) {
    this.value = validate(value);
  }

  public String getValue() {
    return this.value;
  }

  public static <T extends Identifier<T>> T generate(Class<T> identifierClass) {
    try {
      T instance = identifierClass.getDeclaredConstructor(String.class)
          .newInstance(UUID.randomUUID().toString());
      return instance;
    } catch (Exception e) {
      throw new RuntimeException("Error generating identifier", e);
    }
  }

  protected static <T extends Identifier<T>> T from(Class<T> identifierClass, final UUID id) {
    try {
      T instance = identifierClass.getDeclaredConstructor(String.class)
          .newInstance(id.toString().toLowerCase());
      return instance;
    } catch (Exception e) {
      throw new RuntimeException("Error creating identifier from UUID", e);
    }
  }

  private String validate(final String value) {
    try {
      if (Objects.isNull(value)) {
        throw new IllegalArgumentException("Id must not be null");
      }
      UUID.fromString(value);
      return value;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid OrderID format");
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Identifier<?> that = (Identifier<?>) o;
    return Objects.equals(getValue(), that.getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getValue());
  }
}
