package com.deyvisonborges.service.orders.core.modules.management.order;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrderIDTest {
  @Test
  void validateEntityInitialization() {
    final var id = UUID.randomUUID();
    final var orderId = new OrderID(id.toString());
    Assertions.assertNotNull(orderId);
    Assertions.assertEquals(id.toString(), orderId.getValue().toString());
  }  
}
