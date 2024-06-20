package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.util.List;
import java.util.Optional;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.pagination.OrderFilterService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.entities.OrderMongoEntity;
import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.read.repositories.OrderMongoRepository;
import com.deyvisonborges.service.orders.core.domain.pagination.Pagination;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderPaginationQuery;

@Component
public class OrderReadableRepository {
  private final MongoTemplate mongoTemplate;
  private final OrderMongoRepository repository;
  private final OrderSpecification orderSpecification;

  public OrderReadableRepository(MongoTemplate mongoTemplate, final OrderMongoRepository repository, final OrderSpecification orderSpecification) {
    this.mongoTemplate = mongoTemplate;
    this.repository = repository;
    this.orderSpecification = orderSpecification;
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

      final var mongoQuery = OrderFilterService
        .buildQueryFilter(query.filter())
        .with(pageRequest);

      long total = mongoTemplate.count(mongoQuery, OrderMongoEntity.class);

      List<OrderMongoEntity> allEntities = mongoTemplate.find(mongoQuery, OrderMongoEntity.class);
      List<Order> orders = allEntities.stream()
        .map(OrderMongoEntity::toAggregate)
        .toList();

      return new Pagination<>(query.page(), query.perPage(), total, orders);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

}
