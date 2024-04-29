package com.deyvisonborges.service.orders.core.domain.cqrs.springcqrsmodule;

public interface Bus {
  <R, C> R executeCommand(C command);
  <R, Q> R executeQuery(Q query);
}
