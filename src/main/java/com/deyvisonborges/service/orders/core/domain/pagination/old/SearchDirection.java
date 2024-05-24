package com.deyvisonborges.service.orders.core.domain.pagination.old;

public enum SearchDirection {
  ASC,
  DESC;

  public static SearchDirection from(String direction) {
    if (direction != null) {
      switch (direction.toUpperCase()) {
        case "ASC":
        case "ASCENDANT":
          return ASC;
        case "DESC":
        case "DESCENDANT":
          return DESC;
        default:
          throw new IllegalArgumentException("Invalid search direction: " + direction);
      }
    }
    throw new IllegalArgumentException("Search direction cannot be null");
  }
}
