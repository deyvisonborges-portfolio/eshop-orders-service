package com.deyvisonborges.service.orders.core.domain.pagination;

import org.springframework.data.jpa.domain.Specification;

public final class SpecificationUtils {
  private SpecificationUtils() {}

  public static <T> Specification<T> like(final String prop, final String term) {
    return (root, query1, criteriaBuilder) ->
      criteriaBuilder.like(root.get(prop), like(term.toUpperCase()));
  }

  private static String like(String term) {
    return "%" + term + "%";
  }
}
