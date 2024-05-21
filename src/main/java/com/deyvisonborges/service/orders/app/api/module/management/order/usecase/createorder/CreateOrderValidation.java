package com.deyvisonborges.service.orders.app.api.module.management.order.usecase.createorder;

import com.deyvisonborges.service.orders.core.domain.Validator;
import com.deyvisonborges.service.orders.core.domain.primitives.Money;

public class CreateOrderValidation {
  final Validator validation = new Validator();

  public void validate(final CreateOrderCommand command) {
    validation.shouldBeNull("id");
    validation.isRequired("status", command.status() != null ? command.status().name() : null);
    validation.isRequired("items", command.items() != null ? Integer.toString(command.items().size()) : null);
    validation.isRequired("customerId", command.customerId());
    validation.isRequired("paymentsIds", command.paymentsIds() != null ? Integer.toString(command.paymentsIds().size()) : null);
    validateMoneyField(command.subTotal(), "subTotal");
    validateMoneyField(command.shippingFee(), "shippingFee");
    validateMoneyField(command.discount(), "discount");
    validateMoneyField(command.total(), "total");

    validation.validate();
  }

  private void validateMoneyField(Money money, String fieldName) {
    if (money == null) {
      validation.addError(fieldName, fieldName + " cannot be null");
    } else if (money.getAmount().signum() < 0) {
      validation.addError(fieldName, fieldName + " cannot be negative");
    }
  }
}
