package com.deyvisonborges.service.orders.tdd.core.modules.management.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.modules.management.order.Currency;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderID;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderItem;
import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;


// public class OrderTest {
//   private final UUID fakeId = UUID.randomUUID();
//   private final Money fakeMoney = new Money(BigDecimal.valueOf(0.00), "BRL");
//   private final Set<String> emptySet = new HashSet<String>();

//   @Test
//   void validateEntityInitialization() {
//     final var order = Order.emptyFactory();
//     Assertions.assertNotNull(order);
//   }  

//   @Test
//   void givenAValidParameters_whenInstanceOrderEmptyFactory_shouldReturnOk() {
//     final var expectedStatus = OrderStatus.CREATED;
//     final var expectedItems = new HashSet<OrderItem>();
//     final var expectedCustomerId = this.fakeId.toString();
//     final var expectedPaymentsIds = this.emptySet;
//     final var expectedSubTotal = this.fakeMoney; 
//     final var expectedShippingFee = this.fakeMoney; 
//     final var expectedDiscount = this.fakeMoney; 
//     final var expectedTotal = this.fakeMoney; 

//     final var order = Order.factory(
//       expectedStatus, 
//       expectedItems, 
//       expectedCustomerId, 
//       expectedPaymentsIds, 
//       expectedSubTotal, 
//       expectedShippingFee, 
//       expectedDiscount, 
//       expectedTotal
//     );
//     order.setId(OrderID.from(OrderID.class, fakeId));
    
//     Assertions.assertNotNull(order);
//     Assertions.assertEquals(fakeId.toString(), order.getId().getValue().toString());
//     Assertions.assertEquals(expectedStatus, order.getStatus());
//     Assertions.assertEquals(expectedItems, order.getItems());
//     Assertions.assertEquals(expectedCustomerId, order.getCustomerId().toString());
//     Assertions.assertEquals(expectedPaymentsIds, order.getPaymentsIds());
//     Assertions.assertEquals(expectedSubTotal, order.getSubTotal());
//     Assertions.assertEquals(expectedShippingFee, order.getShippingFee());
//     Assertions.assertEquals(expectedDiscount, order.getDiscount());
//     Assertions.assertEquals(expectedTotal, order.getTotal());
//   }
// }
public class OrderTest {

    private Order order;
    private Set<OrderItem> items;

    @Before(value = "")
    public void setUp() {
        // Configuração inicial para cada teste
        items = new HashSet<>();
        items.add(new OrderItem("Item 1", 0, new BigDecimal("10.00")));
        items.add(new OrderItem("Item 2", 0, new BigDecimal("20.00")));

        BigDecimal shippingFee = new BigDecimal("5.00");
        BigDecimal discount = new BigDecimal("2.00");

        // Criando um novo pedido para cada teste
        order = new Order(OrderStatus.CREATED, items, "customer123", shippingFee, discount, Currency.BRL);
    }

    @Test
    public void testOrderCreation() {
        assertEquals(OrderStatus.CREATED, order.getStatus());
        assertEquals(items, order.getItems());
        assertEquals("customer123", order.getCustomerId());
        assertEquals(new BigDecimal("5.00"), order.getShippingFee());
        assertEquals(new BigDecimal("2.00"), order.getDiscount());
        assertEquals(Currency.BRL, order.getCurrency());

        // Testa o cálculo do subtotal
        assertEquals(new BigDecimal("30.00"), order.getSubTotal());

        // Testa o cálculo do total
        assertEquals(new BigDecimal("33.00"), order.getTotal());
    }
}
