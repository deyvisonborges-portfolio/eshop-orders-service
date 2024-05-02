package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.getorderbyid;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.core.domain.cqrs.QueryHandler;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
// import com.deyvisonborges.service.orders.core.modules.management.order.repository.OrderRepositoryGateway;

@Service
public class GetOrderByIdUseCase implements QueryHandler<Order, String> {
  // private final OrderRepositoryGateway gateway;

  // public GetOrderByIdUseCase(final OrderRepositoryGateway gateway) {
  //   this.gateway = gateway;
  // }

  @Override
  public Order handle(String query) {
    return null;
  }
}
