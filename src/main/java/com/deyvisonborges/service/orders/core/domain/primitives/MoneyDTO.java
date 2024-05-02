package com.deyvisonborges.service.orders.core.domain.primitives;

import java.math.BigDecimal;

public record MoneyDTO(
  BigDecimal amount,
  String currency
) {
  
}
