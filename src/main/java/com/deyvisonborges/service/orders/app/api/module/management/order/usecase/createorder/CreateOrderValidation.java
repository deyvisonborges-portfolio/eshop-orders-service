package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import java.util.Set;

import com.deyvisonborges.service.orders.core.domain.Validator;
import com.deyvisonborges.service.orders.core.modules.management.order.dto.OrderItemDTO;

public class CreateOrderValidation {
  final Validator validation = new Validator();

  public void validate(final CreateOrderCommand command) {
    validation.isRequired("status", command.status() != null ? command.status().name() : null);
    validation.isRequired("items", command.items() != null ? Integer.toString(command.items().size()) : null);
    validation.isRequired("customerId", command.customerId());
    String currency = null;
    validateItems(command.items(), currency);
    validation.validate();
  }

  private void validateItems(Set<OrderItemDTO> items, String expectedCurrency) {
    if (items != null && !items.isEmpty()) {
      for (OrderItemDTO item : items) {
        validation.isRequired("productId", item.productId());
        validation.isRequired("quantity", Integer.toString(item.quantity()));

        if (item.quantity() <= 0) {
          validation.addError("quantity", "quantity must be greater than zero");
        }

        if (item.price() == null) {
          validation.addError("price", "price cannot be null");
        } else {
          if (expectedCurrency != null && !expectedCurrency.equals(item.price())) {
            validation.addError("currency", "All items must have the same currency as other money fields");
          }
        }
      }
    }
  }
}
