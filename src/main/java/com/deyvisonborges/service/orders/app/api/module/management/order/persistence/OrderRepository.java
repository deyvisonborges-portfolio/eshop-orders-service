package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.write.entities.OrderItemJPAEntity;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.write.entities.OrderJPAEntity;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.write.repositories.OrderJPARepository;
import com.deyvisonborges.service.orders.core.domain.pagination.Pagination;
import com.deyvisonborges.service.orders.core.domain.pagination.SpecificationUtils;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderPaginationQuery;
import com.deyvisonborges.service.orders.core.modules.management.order.repository.OrderRepositoryGateway;

import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderRepository implements OrderRepositoryGateway {
  private final OrderJPARepository jpaRepository;

  public OrderRepository(final OrderJPARepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  @Transactional
  public void save(Order order) {
    try {
      if (this.findById(order.getId().getValue()).isPresent()) {
        throw new RuntimeException("Order already exists");
      }

      final var orderToSave = OrderJPAEntity.toJPAEntity(order);
      final var savedOrder = this.jpaRepository.save(orderToSave);

      for (OrderItemJPAEntity item : savedOrder.getItems()) {
        item.setOrder(savedOrder);
      }
      
      this.jpaRepository.save(savedOrder);
    } catch (Exception e) {
      throw new RuntimeException("Fail to save Order on JPA Repository: " + e.getMessage());
    }
  }

  public void reply(Order order) {
    try {
      if (this.findById(order.getId().getValue()).isPresent()) {
        throw new RuntimeException("Order already exists");
      }

      final var orderToSave = OrderJPAEntity.toJPAEntity(order);
      final var savedOrder = this.jpaRepository.save(orderToSave);

      for (OrderItemJPAEntity item : savedOrder.getItems()) {
        item.setOrder(savedOrder);
      }
      
      this.jpaRepository.save(savedOrder);
    } catch (Exception e) {
      throw new RuntimeException("Fail to save Order on JPA Repository: " + e.getMessage());
    }
  }

  @Override
  public void saveAll(List<Order> orders) {
    try {
      List<OrderJPAEntity> entities = orders.stream()
        .map(OrderJPAEntity::toJPAEntity)
        .collect(Collectors.toList());
      this.jpaRepository.saveAll(entities);
    } catch (Exception e) {
      throw new RuntimeException("Fail to save all Orders on JPA Repository");
    }
  }

  @Override
  public List<Order> findAll() {
    try {
      return this.jpaRepository.findAll()
        .stream()
        .map(OrderJPAEntity::toAggregate)
        .collect(Collectors.toList());
    } catch (Exception e) {
      throw new RuntimeException("Fail to return all orders from JPA Repository");
    }
  }

  @Override
  public void deleteById(String id) {
    try {
      this.jpaRepository.deleteById(id);
    } catch (Exception e) {
      throw new RuntimeException("Fail to DELETE ORDER BY ID in JPA Repository");
    }
  }

  @Transactional
  public Optional<Order> findById(String id) {
    return this.jpaRepository.findById(id)
      .map(OrderJPAEntity::toAggregate);
  }

  @Override
  public Pagination<Order> findAll(OrderPaginationQuery query) {
    try {
      /*
       * Pagination
       */
      final var pageRequest = PageRequest.of(
        query.page(),
        query.perPage(),
        Sort.by(Sort.Direction.fromString(query.direction().name()), query.sort())
      );

      // Configurar especificação de consulta dinâmica
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

      final var pageResult = this.jpaRepository.findAll(Specification.where(specifications), pageRequest);

      return new Pagination<>(
        pageResult.getNumber(),
        pageResult.getSize(),
        pageResult.getTotalElements(),
        pageResult.map(OrderJPAEntity::toAggregate).toList()
      );
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public void update(Order order) {
    try {
      this.jpaRepository.saveAndFlush(OrderJPAEntity.toJPAEntity(order));
    } catch (Exception e) {
      throw new RuntimeException("Fail to UPDATE ORDER in JPA Repository");
    }
  }
}
