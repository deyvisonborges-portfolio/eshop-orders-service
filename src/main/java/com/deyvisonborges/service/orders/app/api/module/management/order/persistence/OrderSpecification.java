package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderSpecification {
  public Query buildQuery(final Map<String, String> fieldTerms) {
    final var query = new Query();
    if (fieldTerms != null && !fieldTerms.isEmpty()) {
      for (Map.Entry<String, String> entry : fieldTerms.entrySet()) {
        if (entry.getKey() != null && !entry.getKey().isBlank() &&
          entry.getValue() != null && !entry.getValue().isBlank()) {
          query.addCriteria(Criteria.where(entry.getKey()).regex(entry.getValue(), "i"));
        }
      }
    }
    return query;
  }
}
