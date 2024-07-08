package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.listorders;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderReadableRepository;
import com.deyvisonborges.service.orders.core.domain.pagination.Pagination;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderPaginationQuery;

@Service
public class ListOrdersQueryHandler {
  private final OrderReadableRepository repository;

  public ListOrdersQueryHandler(final OrderReadableRepository repository) {
    this.repository = repository;
  }
  
  public Pagination<ListOrdersQueryOutput> handle(final OrderPaginationQuery query) {
    return this.repository.findAll(query).map(ListOrdersQueryOutput::from);
  }
}
