package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.getorderbyid;

import java.text.MessageFormat;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderRepository;
import com.deyvisonborges.service.orders.app.exception.NotFoundException;
import com.deyvisonborges.service.orders.core.domain.cqrs.QueryHandler;

@Service
public class GetOrderByIdQueryHandler implements QueryHandler<GetOrderByIdOutput, String> {
  private final OrderRepository repository;

  public GetOrderByIdQueryHandler(final OrderRepository repository) {
    this.repository = repository;
  }

  @Override
  public GetOrderByIdOutput handle(final String id) {
    final var orderId = id;
    return this.repository.findById(id)
      .map(GetOrderByIdOutput::from)
      .orElseThrow(() -> new NotFoundException(
        MessageFormat.format("Not found Order with id: {0}", orderId))
      );
  }
}
