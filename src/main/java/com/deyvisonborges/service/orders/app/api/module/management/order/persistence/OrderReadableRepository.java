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
import com.deyvisonborges.service.orders.core.domain.pagination.Pagination;
import com.deyvisonborges.service.orders.core.domain.pagination.SpecificationUtils;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderPaginationQuery;

@Repository
public class OrderReadableRepository {
  private final OrderRedisRepository repository;

  public OrderReadableRepository(final OrderRedisRepository repository) {
    this.repository = repository;
  }

  // SAVE
  public void save(final Order order) {
    try {
      if (this.findById(order.getId().getValue()).isPresent())
        throw new RuntimeException("Order already exists");     
      this.repository.save(OrderRedisEntity.toJPAEntity(order));
    } catch (Exception e) {
      throw new RuntimeException("Fail to save Order on redis repository: " + e.getMessage());
    }
  }

  // FIND BY ID
  public Optional<Order> findById(String id) {
    try {
      return this.repository.findById(id)
        .map(OrderRedisEntity::toAggregate);
    } catch (Exception e) {
      throw new RuntimeException("Fail to return order with id: " + id, e);
    }
  }


  // DELETE BY ID
  public void deleteById(String id) {
    try {
      this.repository.deleteById(id);
    } catch (Exception e) {
      throw new RuntimeException("Fail to DELETE ORDER BY ID in Redis Repository");
    }
  }

  // FIND ALL
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
          Specification<OrderRedisEntity> spec = Specification.where(null);
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
