package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.util.List;
import java.util.Optional;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.pagination.OrderFilterService;
import jakarta.transaction.Transactional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
// import org.springframework.data.mongodb.core.query.Criteria;
// import org.springframework.data.mongodb.core.query.Query;
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

  public OrderReadableRepository(MongoTemplate mongoTemplate, final OrderMongoRepository repository) {
    this.mongoTemplate = mongoTemplate;
    this.repository = repository;
  }

  @CacheEvict(value = "orders", allEntries = true)
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

  @Cacheable("orders")
  public Optional<Order> findById(String id) {
    return this.repository.findById(id)
      .map(OrderMongoEntity::toAggregate);
  }

  @CacheEvict(value = "orders", key = "#id", allEntries = true)
  public void deleteById(String id) {
    try {
      this.repository.deleteById(id);
    } catch (Exception e) {
      throw new RuntimeException("Fail to DELETE ORDER BY ID in Redis Repository");
    }
  }
  
  @Cacheable(value = "orders")
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

  // public List<Order> delayedOrders() {
  //   public List<Order> delayedOrders() {
  //       Query query = new Query(isDelayed());
  //       query.with(Sort.by(Sort.Direction.ASC, "createDate"));
  //       return orders.findAll(query);
  //   }

  //   public static Criteria isDelayed() {
  //       Instant date = Instant.now().minus(14, ChronoUnit.DAYS);
  //       return new Criteria().andOperator(
  //           Criteria.where("state").is(Status.OPEN),
  //           Criteria.where("createDate").lt(date)
  //       );
  //   }
  // }

  // TODO: add criteria to return all orders total
  // public BigDecimal findTotalAllOrdersByCustomerId(final String customerId) {
  //   final var aggregations = Aggregation.newAggregation(
  //     Aggregation.match(Criteria.where("customerId").is(customerId)),
  //     Aggregation.group().sum("total").as("total")
  //   );

  //   final var result = mongoTemplate.aggregate(aggregations, "orders", Document.class);
  //   return new BigDecimal(result.getUniqueMappedResult().get("total").toString());
  // }
}
