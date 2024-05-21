package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import java.util.Objects;
import java.util.Set;

import com.deyvisonborges.service.orders.core.domain.Validator;
import com.deyvisonborges.service.orders.core.domain.primitives.Money;
import com.deyvisonborges.service.orders.core.modules.management.order.dto.OrderItemDTO;

public class CreateOrderValidation {
  final Validator validation = new Validator();

  public void validate(final CreateOrderCommand command) {
    validation.isRequired("status", command.status() != null ? command.status().name() : null);
    validation.isRequired("items", command.items() != null ? Integer.toString(command.items().size()) : null);
    validation.isRequired("customerId", command.customerId());
    validation.isRequired("paymentsIds",
      command.paymentsIds() != null ? Integer.toString(command.paymentsIds().size()) : null);

    validateMoneyField(command.subTotal(), "subTotal");
    validateMoneyField(command.shippingFee(), "shippingFee");
    validateMoneyField(command.discount(), "discount");
    validateMoneyField(command.total(), "total");

    validateItems(command.items());

    validation.validate();
  }

  private void validateMoneyField(Money money, String fieldName) {
    if (money == null) {
      validation.addError(fieldName, fieldName + " cannot be null");
    } else if (Objects.isNull(money.getCurrency())) {
      validation.addError(fieldName, fieldName + " currency cannot be null");
    } else if (money.getAmount().signum() < 0) {
      validation.addError(fieldName, fieldName + " cannot be negative");
    }
  }

  private void validateItems(Set<OrderItemDTO> items) {
    if (items != null) {
      for (OrderItemDTO item : items) {
        validation.isRequired("productId", item.productId());
        validation.isRequired("quantity", Integer.toString(item.quantity()));

        if (item.quantity() <= 0) {
          validation.addError("quantity", "quantity must be greater than zero");
        }

        if (item.price() == null) {
          validation.addError("price", "price cannot be null");
        } else {
          validateMoneyField(new Money(item.price().amount(), item.price().currency()), "price");
        }
      }
    }
  }
}
