package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.updateorder;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.OrderWritableRepository;
import com.deyvisonborges.service.orders.app.exception.NotFoundException;
import com.deyvisonborges.service.orders.core.modules.management.order.Order;
import com.deyvisonborges.service.orders.core.modules.management.order.dto.OrderItemDTO;

@Service
public class UpdateOrderCommandHandler {
  private final OrderWritableRepository orderRepository;

  public UpdateOrderCommandHandler(final OrderWritableRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  public void handle(final String orderId, final UpdateOrderCommand command) {
    Order order = this.orderRepository.findById(orderId)
      .orElseThrow(() -> new NotFoundException("Order not found"));
    if (command.status() != null)
      order.setStatus(command.status());
    if (command.items() != null) {
      order.setItems(
        command.items().stream()
          .map(OrderItemDTO::toAggregate)
          .collect(Collectors.toSet()));
    }
    if (command.customerId() != null)
      order.setCustomerId(command.customerId());
    if (command.subTotal() != null)
      order.setSubTotal(command.subTotal());
    if (command.shippingFee() != null)
      order.setShippingFee(command.shippingFee());
    if (command.discount() != null)
      order.setDiscount(command.discount());
    if (command.total() != null)
      order.setTotal(command.total());
  }
}
