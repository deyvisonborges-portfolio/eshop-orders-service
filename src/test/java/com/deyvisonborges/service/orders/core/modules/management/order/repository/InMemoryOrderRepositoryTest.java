package com.deyvisonborges.service.orders.core.modules.management.order.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;

public class InMemoryOrderRepositoryTest {

  private InMemoryOrderRepository orderRepository;

  @BeforeEach
  public void setUp() {
    orderRepository = new InMemoryOrderRepository();
  }

  @Test
  public void testSaveAndFindAll() {
    Order order = createSampleOrder();
    orderRepository.save(order);

    List<Order> orders = orderRepository.findAll();
    assertEquals(1, orders.size());
    assertEquals(order, orders.get(0));
  }

  @Test
  public void testSaveAllAndFindAll() {
    Order order1 = createSampleOrder();
    Order order2 = createSampleOrder();
    orderRepository.saveAll(List.of(order1, order2));

    List<Order> orders = orderRepository.findAll();
    assertEquals(2, orders.size());
    assertTrue(orders.contains(order1));
    assertTrue(orders.contains(order2));
  }

  private Order createSampleOrder() {
    return new Order(
      OrderStatus.CREATED,
      new HashSet<>(),
      "customer123",
      new HashSet<>(),
      new Money(BigDecimal.valueOf(100.00), "USD"),
      new Money(BigDecimal.valueOf(10.00), "USD"),
      new Money(BigDecimal.valueOf(0.00), "USD"),
      new Money(BigDecimal.valueOf(110.00), "USD")
    );
  }
}