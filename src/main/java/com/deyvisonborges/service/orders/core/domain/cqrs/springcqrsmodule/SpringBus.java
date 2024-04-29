package com.deyvisonborges.service.orders.core.domain.cqrs.springcqrsmodule;

import com.deyvisonborges.service.orders.core.domain.cqrs.CommandHandler;
import com.deyvisonborges.service.orders.core.domain.cqrs.QueryHandler;

public class SpringBus implements Bus {
  private final Registry registry;

  public SpringBus(final Registry registry) {
    this.registry = registry;
  }

  @Override
  public <R, C> R executeCommand(C command) {
    @SuppressWarnings("unchecked")
    CommandHandler<R, C> commandHandler = (CommandHandler<R, C>) registry.getCommand(command.getClass());
    return commandHandler.handle(command);
  }

  @Override
  public <R, Q> R executeQuery(Q query) {
    @SuppressWarnings("unchecked")
    QueryHandler<R, Q> queryHandler = (QueryHandler<R, Q>) registry.getQuery(query.getClass());
    return queryHandler.handle(query);
  }
}
