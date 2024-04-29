package com.deyvisonborges.service.orders.core.domain.cqrs.springcqrsmodule;

import org.springframework.context.ApplicationContext;

import com.deyvisonborges.service.orders.core.domain.cqrs.QueryHandler;

public class QueryProvider<H extends QueryHandler<?, ?>> {
  private final ApplicationContext applicationContext;
  private final Class<H> type;

  public QueryProvider(
    final ApplicationContext applicationContext,
    final Class<H> type
  ) {
    this.applicationContext = applicationContext;
    this.type = type;
  }

  public H get() {
    return applicationContext.getBean(type);
  }
}
