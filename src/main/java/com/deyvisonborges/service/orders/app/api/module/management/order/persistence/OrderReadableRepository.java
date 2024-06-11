package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.entities.OrderRedisEntity;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.repositories.OrderRedisRepository;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.write.entities.OrderJPAEntity;
import com.deyvisonborges.service.orders.core.domain.pagination.Pagination;
import com.deyvisonborges.service.orders.core.domain.pagination.SpecificationUtils;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderPaginationQuery;

import jakarta.transaction.Transactional;

@Repository
public class OrderReadableRepository {
  private final OrderRedisRepository repository;

  public OrderReadableRepository(final OrderRedisRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public void save() {
    final var order = new OrderRedisEntity();
    this.repository.save(order);
  }

  public Optional<Order> findById(String id) {
    try {
      return this.repository.findById(id)
        .map(OrderRedisEntity::toAggregate);
    } catch (Exception e) {
      throw new RuntimeException("Fail to return order with id: " + id, e);
    }
  }

  public Pagination<Order> findAll(final OrderPaginationQuery query) {
    try {
      final var pageRequest = PageRequest.of(
        query.page(),
        query.perPage(),
        Sort.by(Sort.Direction.fromString(query.direction().name()), query.sort())
      );

      final var specifications = query.terms().stream()
        .filter(term -> term != null && !term.isBlank())
        .map(term -> {
          String[] fieldsToSearch = {"id", "status", "customerId"};
          Specification<OrderJPAEntity> spec = Specification.where(null);
          for (String field : fieldsToSearch) {
            spec = spec.or(SpecificationUtils.like(field, term));
          }
          return spec;
        })
        .reduce(Specification::and)
        .orElse(null);

      final var pageResult = this.repository.findAll(Specification.where(specifications), pageRequest);

      List<Order> orders = pageResult.getContent().stream()
        .map(OrderRedisEntity::toAggregate)
        .collect(Collectors.toList());

      return new Pagination<>(
        pageResult.getNumber(),
        pageResult.getSize(),
        pageResult.getTotalElements(),
        orders
      );
    } catch (Exception e) {
      throw new RuntimeException("Fail to return all orders from redis repository", e);
    }
  }
}
