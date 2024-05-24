package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.listorders;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderRepository;
import com.deyvisonborges.service.orders.core.domain.pagination.old.Pagination;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderPaginationQuery;

@Service
public class ListOrdersQueryHandler {
  private final OrderRepository repository;

  public ListOrdersQueryHandler(final OrderRepository repository) {
    this.repository = repository;
  }
  
  public Pagination<ListOrdersQueryOutput> handle(final OrderPaginationQuery query) {
    return this.repository.findAll(query)
      .map(ListOrdersQueryOutput::from);
  }
}
