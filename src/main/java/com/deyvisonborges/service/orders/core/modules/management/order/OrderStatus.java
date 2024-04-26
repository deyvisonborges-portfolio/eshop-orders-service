package com.deyvisonborges.service.orders.core.modules.management.order;

public enum OrderStatus {
  CREATED("CRIADO"),
  UPDATED("ATUALIZADO"),
  CANCELLED("CANCELADO"),
  SENT("ENVIADO"),
  DELIVERED("ENTREGUE");

  private final String value;

  OrderStatus(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
