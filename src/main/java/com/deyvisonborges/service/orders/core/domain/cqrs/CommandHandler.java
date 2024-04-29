package com.deyvisonborges.service.orders.core.domain.cqrs;

public interface CommandHandler<R, C> {
  R handle(C command);
}
