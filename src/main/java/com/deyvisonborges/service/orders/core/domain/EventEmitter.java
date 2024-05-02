package com.deyvisonborges.service.orders.core.domain;

public abstract class EventEmitter<T> {
  protected abstract void applyOn(T command);
}