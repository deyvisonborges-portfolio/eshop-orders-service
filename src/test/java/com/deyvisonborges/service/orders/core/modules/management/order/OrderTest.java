package com.deyvisonborges.service.orders.core.modules.management.order;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.deyvisonborges.service.orders.core.primitives.Money;


public class OrderTest {
  private final UUID fakeId = UUID.randomUUID();
  private final Money fakeMoney = new Money(BigDecimal.valueOf(0.00), "BRL");
  private final Set<String> emptySet = new HashSet<String>();

  @Test
  void validateEntityInitialization() {
    final var order = Order.emptyFactory();
    Assertions.assertNotNull(order);
  }  

  @Test
  void givenAValidParameters_whenInstanceOrderEmptyFactory_shouldReturnOk() {
    final var expectedStatus = OrderStatus.CREATED;
    final var expectedItems = new HashSet<OrderItem>();
    final var expectedCustomerId = this.fakeId.toString();
    final var expectedPaymentsIds = this.emptySet;
    final var expectedSubTotal = this.fakeMoney; 
    final var expectedShippingFee = this.fakeMoney; 
    final var expectedDiscount = this.fakeMoney; 
    final var expectedTotal = this.fakeMoney; 

    final var order = Order.factory(
      expectedStatus, 
      expectedItems, 
      expectedCustomerId, 
      expectedPaymentsIds, 
      expectedSubTotal, 
      expectedShippingFee, 
      expectedDiscount, 
      expectedTotal
    );
    order.setId(OrderID.from(OrderID.class, fakeId));
    
    Assertions.assertNotNull(order);
    Assertions.assertEquals(fakeId.toString(), order.getId().getValue().toString());
    Assertions.assertEquals(expectedStatus, order.getStatus());
    Assertions.assertEquals(expectedItems, order.getItems());
    Assertions.assertEquals(expectedCustomerId, order.getCustomerId().toString());
    Assertions.assertEquals(expectedPaymentsIds, order.getPaymentsIds());
    Assertions.assertEquals(expectedSubTotal, order.getSubTotal());
    Assertions.assertEquals(expectedShippingFee, order.getShippingFee());
    Assertions.assertEquals(expectedDiscount, order.getDiscount());
    Assertions.assertEquals(expectedTotal, order.getTotal());
  }
}