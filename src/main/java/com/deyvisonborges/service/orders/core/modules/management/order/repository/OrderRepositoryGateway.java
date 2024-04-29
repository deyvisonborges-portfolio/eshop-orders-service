package com.deyvisonborges.service.orders.core.modules.management.order.repository;

import java.util.List;

import com.deyvisonborges.service.orders.core.modules.management.order.Order;

public interface OrderRepositoryGateway {
  void save(Order order);
  void saveAll(List<Order> orders);
  List<Order> findAll();
}
