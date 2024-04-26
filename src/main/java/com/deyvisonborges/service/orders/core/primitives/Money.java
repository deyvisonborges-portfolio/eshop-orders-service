package com.deyvisonborges.service.orders.core.primitives;

import java.math.BigDecimal;

public class Money {
  private final BigDecimal amount;
  private final String currency;

  public Money(final BigDecimal amount, final String currency) {
    this.amount = amount;
    this.currency = currency;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public String getCurrency() {
    return currency;
  }

  public Money add(Money money) {
    if (!this.currency.equals(money.currency))
      throw new IllegalArgumentException("Moedas diferentes não podem ser somadas.");

    final var newAmount = this.amount.add(money.amount);
    return new Money(newAmount, this.currency);
  }

  public Money subtract(Money money) {
    if (!this.currency.equals(money.currency))
      throw new IllegalArgumentException("Moedas diferentes não podem ser subtraídas.");

    final var newAmount = this.amount.subtract(money.amount);
    return new Money(newAmount, this.currency);
  }

  public String toString() {
    return amount + " " + currency;
  }
}
