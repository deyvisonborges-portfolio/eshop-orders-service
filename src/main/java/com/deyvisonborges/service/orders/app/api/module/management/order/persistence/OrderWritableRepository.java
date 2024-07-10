package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.write.entities.OrderItemJPAEntity;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.write.entities.OrderJPAEntity;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.write.repositories.OrderJPARepository;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;

import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderWritableRepository {
  private final OrderJPARepository jpaRepository;

  public OrderWritableRepository(final OrderJPARepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @CacheEvict(value = "orders", allEntries = true)
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

  @Transactional
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

  @CacheEvict(value = "orders", allEntries = true)
  public void deleteById(String id) {
    try {
      this.jpaRepository.deleteById(id);
    } catch (Exception e) {
      throw new RuntimeException("Fail to DELETE ORDER BY ID in JPA Repository");
    }
  }

  @Cacheable(value = "orders")
  @Transactional
  public Optional<Order> findById(String id) {
    return this.jpaRepository.findById(id)
      .map(OrderJPAEntity::toAggregate);
  }

  @CachePut(value = "orders", key = "#order.id")
  public void update(Order order) {
    try {
      this.jpaRepository.saveAndFlush(OrderJPAEntity.toJPAEntity(order));
    } catch (Exception e) {
      throw new RuntimeException("Fail to UPDATE ORDER in JPA Repository");
    }
  }
}
