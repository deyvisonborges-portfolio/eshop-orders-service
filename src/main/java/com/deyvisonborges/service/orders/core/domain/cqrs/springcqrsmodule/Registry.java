package com.deyvisonborges.service.orders.core.domain.cqrs.springcqrsmodule;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;

import com.deyvisonborges.service.orders.core.domain.cqrs.Command;
import com.deyvisonborges.service.orders.core.domain.cqrs.CommandHandler;
import com.deyvisonborges.service.orders.core.domain.cqrs.QueryHandler;

public class Registry {
  @SuppressWarnings("rawtypes")
  private Map<Class<?>, CommandProvider> commandProviderMap = new HashMap<>();

  @SuppressWarnings("rawtypes")
  private Map<Class<?>, QueryProvider> queryProviderMap = new HashMap<>();

  public Registry(final ApplicationContext applicationContext) {
    String[] names = applicationContext.getBeanNamesForType(CommandHandler.class);

    for(String name : names) {
      registerCommand(applicationContext, name);
    }

    names = applicationContext.getBeanNamesForType(QueryHandler.class);

    for(String name : names) {
      registerQuery(applicationContext, name);
    }
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void registerCommand(final ApplicationContext applicationContext, final String name) {
    Class<CommandHandler<?, ?>> handlerClass = (Class<CommandHandler<?, ?>>) applicationContext.getType(name);
    
    @SuppressWarnings("null")
    Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, CommandHandler.class);
    
    @SuppressWarnings({ "null" })
    Class<? extends Command> commandType = (Class<? extends Command>) generics[1];
    
    commandProviderMap.put(commandType, new CommandProvider(applicationContext, handlerClass));
  }

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void registerQuery(final ApplicationContext applicationContext, final String name) {
    Class<QueryHandler<?, ?>> handlerClass = (Class<QueryHandler<?, ?>>) applicationContext.getType(name);
    
    @SuppressWarnings("null")
    Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, QueryHandler.class);
    
    @SuppressWarnings({ "null" })
    Class<? extends Command> queryType = (Class<? extends Command>) generics[1];
    
    queryProviderMap.put(queryType, new QueryProvider(applicationContext, handlerClass));
  }

  @SuppressWarnings("unchecked")
  <R, C> CommandHandler<R, C> getCommand(Class<C> commandClass) {
    return commandProviderMap.get(commandClass).get();
  }

  @SuppressWarnings("unchecked")
  <R, Q> QueryHandler<R, Q> getQuery(Class<Q> commandClass) {
    return queryProviderMap.get(commandClass).get();
  }
}
