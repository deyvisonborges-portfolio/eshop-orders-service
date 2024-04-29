package com.deyvisonborges.service.orders.core.modules.management.order.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.deyvisonborges.service.orders.core.modules.management.order.Order;

public class InMemoryOrderRepository implements OrderRepositoryGateway {
  private Map<String, Order> repository = new ConcurrentHashMap<>();

  @Override
  public void save(Order order) {
    repository.put(order.getId().getValue(), order);
  }

  @Override
  public void saveAll(List<Order> orders) {
    orders.forEach(order -> repository.put(order.getId().getValue(), order));
  }

  @Override
  public List<Order> findAll() {
    return new ArrayList<>(repository.values());
  }
}
