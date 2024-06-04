package com.deyvisonborges.service.orders.core.modules.management.order.repository;

import java.util.List;
import java.util.Optional;

import com.deyvisonborges.service.orders.core.domain.pagination.Pagination;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderPaginationQuery;

public interface OrderRepositoryGateway {
  void save(Order order);
  void saveAll(List<Order> orders);
  List<Order> findAll();
  Pagination<Order> findAll(OrderPaginationQuery query);
  void deleteById(String id);
  Optional<Order> findById(String id);
  void update(Order order);
}
