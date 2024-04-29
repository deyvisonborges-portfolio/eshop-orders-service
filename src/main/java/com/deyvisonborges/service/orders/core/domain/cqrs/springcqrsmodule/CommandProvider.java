package com.deyvisonborges.service.orders.core.domain.cqrs.springcqrsmodule;

import org.springframework.context.ApplicationContext;

import com.deyvisonborges.service.orders.core.domain.cqrs.CommandHandler;

public class CommandProvider<H extends CommandHandler<?, ?>> {
  private final ApplicationContext applicationContext;
  private final Class<H> type;

  public CommandProvider(
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
