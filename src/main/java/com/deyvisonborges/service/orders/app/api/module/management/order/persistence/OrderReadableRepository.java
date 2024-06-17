package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    // SAVE
    public void save(final Order order) {
        try {
            if (this.findById(order.getId().getValue()).isPresent())
                throw new RuntimeException("Order already exists");
            this.repository.save(OrderMongoEntity.toJPAEntity(order));
        } catch (Exception e) {
            throw new RuntimeException("Fail to save Order on redis repository: " + e.getMessage());
        }
    }

    // FIND BY ID
    public Optional<Order> findById(String id) {
        return this.repository.findById(id)
                .map(OrderMongoEntity::toAggregate);
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
            // final var pageRequest = PageRequest.of(
            //         query.page(),
            //         query.perPage(),
            //         Sort.by(Sort.Direction.fromString(query.direction().name()), query.sort())
            // );

            // Obter todos os dados do Redis
            System.out.println("========> 01");
            Iterable<OrderMongoEntity> allEntities = this.repository.findAll();
            System.out.println("========> 02");

            // Filtrar os dados manualmente
            List<OrderMongoEntity> filteredEntities = StreamSupport.stream(allEntities.spliterator(), false)
                    .filter(entity -> matchesQuery(entity, query))
                    .collect(Collectors.toList());

            // // Paginar os dados manualmente
            // int total = filteredEntities.size();
            // int start = Math.min((int) pageRequest.getOffset(), total);
            // int end = Math.min((start + pageRequest.getPageSize()), total);
            // List<Order> orders = filteredEntities.subList(start, end).stream()
            //         .map(OrderRedisEntity::toAggregate)
            //         .collect(Collectors.toList());

            return new Pagination(0, 0, 0, filteredEntities);
        } catch (Exception e) {
            System.out.println(e.getSuppressed());
            throw new RuntimeException(e.getMessage());
        }
    }

    // Método auxiliar para verificar se uma entidade corresponde à consulta
    private boolean matchesQuery(OrderMongoEntity entity, OrderPaginationQuery query) {
        for (String term : query.terms()) {
            if (term != null && !term.isBlank()) {
                boolean matches = false;
                if (entity.getId() != null && entity.getId().contains(term)) {
                    matches = true;
                }
                if (entity.getStatus() != null && entity.getStatus().toString().contains(term)) {
                    matches = true;
                }
                if (entity.getCustomerId() != null && entity.getCustomerId().contains(term)) {
                    matches = true;
                }
                if (!matches) {
                    return false;
                }
            }
        }
        return true;
    }
}
