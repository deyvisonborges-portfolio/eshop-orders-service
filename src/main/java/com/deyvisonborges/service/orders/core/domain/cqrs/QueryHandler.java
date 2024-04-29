package com.deyvisonborges.service.orders.core.domain.cqrs;

public interface QueryHandler<R, Q> {
  R handle(Q query);
}
