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

    String currency = null;

    currency = validateMoneyField(command.subTotal(), "subTotal", currency);
    currency = validateMoneyField(command.shippingFee(), "shippingFee", currency);
    currency = validateMoneyField(command.discount(), "discount", currency);

    validateItems(command.items(), currency);

    validation.validate();
  }

  private String validateMoneyField(Money money, String fieldName, String expectedCurrency) {
    if (money == null) {
      validation.addError(fieldName, fieldName + " cannot be null");
    } else if (Objects.isNull(money.getCurrency())) {
      validation.addError(fieldName, fieldName + " currency cannot be null");
    } else if (money.getAmount().signum() < 0) {
      validation.addError(fieldName, fieldName + " cannot be negative");
    } else if (expectedCurrency == null) {
      expectedCurrency = money.getCurrency();
    } else if (!expectedCurrency.equals(money.getCurrency())) {
      validation.addError(fieldName, fieldName + " currency must be the same as other money fields");
    }
    return expectedCurrency;
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
          validateMoneyField(new Money(item.price().amount(), item.price().currency()), "price", expectedCurrency);

          if (expectedCurrency != null && !expectedCurrency.equals(item.price().currency())) {
            validation.addError("currency", "All items must have the same currency as other money fields");
          }
        }
      }
    }
  }
}
