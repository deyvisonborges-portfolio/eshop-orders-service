package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.entities.OrderMongoEntity;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.repositories.OrderMongoRepository;
import com.deyvisonborges.service.orders.core.domain.pagination.Pagination;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderPaginationQuery;

@Component
public class OrderReadableRepository {
  private final OrderMongoRepository repository;

  public OrderReadableRepository(final OrderMongoRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public void save(final Order order) {
    try {
      if (this.findById(order.getId().getValue()).isPresent())
        throw new RuntimeException("Order already exists");
      this.repository.save(OrderMongoEntity.toMongoEntity(order));
    } catch (Exception e) {
      throw new RuntimeException("Fail to save Order on redis repository: " + e.getMessage());
    }
  }

  public Optional<Order> findById(String id) {
    return this.repository.findById(id)
      .map(OrderMongoEntity::toAggregate);
  }

  public void deleteById(String id) {
    try {
      this.repository.deleteById(id);
    } catch (Exception e) {
      throw new RuntimeException("Fail to DELETE ORDER BY ID in Redis Repository");
    }
  }

  public Pagination<Order> findAll(final OrderPaginationQuery query) {
    try {
      final var pageRequest = PageRequest.of(
        query.page(),
        query.perPage(),
        Sort.by(Sort.Direction.fromString(query.direction().name()), query.sort())
      );

      final var pageResult = this.repository.findAll(pageRequest);
      final var orders = pageResult.stream()
        .map(OrderMongoEntity::toAggregate)
        .toList();
      return new Pagination<>(query.page(), query.perPage(), pageResult.getTotalElements(), orders);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }
}
