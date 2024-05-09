package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.repository.OrderRepositoryGateway;

@Repository
public class OrderRepository implements OrderRepositoryGateway {
  private final OrderJPARepository jpaRepository;

  public OrderRepository(final OrderJPARepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public void save(Order order) {
    try {
      final var orderToSave = OrderJPAEntity.toJPAEntity(order);
      System.out.println("===> OLD: " + orderToSave.getId());
      final var after = this.jpaRepository.save(orderToSave);
      System.out.println("===> NEW: " + after.getId());
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
      this.jpaRepository.deleteById(UUID.fromString(id));
    } catch (Exception e) {
      throw new RuntimeException("Fail to DELETE ORDER BY ID in JPA Repository");
    }
  }

  @Override
  public Optional<Order> findById(String id) {
    final var order = this.jpaRepository.findById(UUID.fromString(id))
      .orElseThrow(() -> new RuntimeException("Not found order with id " + id));
    return Optional.of(OrderJPAEntity.toAggregate(order));
  }
}