package com.deyvisonborges.service.orders.core.domain.pagination;

public enum SearchDirection {
  ASCENDANT,
  DESCENDANT;

  public static SearchDirection from(String direction) {
    if (direction != null) {
      switch (direction.toLowerCase()) {
        case "ascendant":
          return ASCENDANT;
        case "descendant":
          return DESCENDANT;
        default:
          throw new IllegalArgumentException("Invalid search direction: " + direction);
      }
    }
    throw new IllegalArgumentException("Invalid search direction: " + direction);
  }
}
