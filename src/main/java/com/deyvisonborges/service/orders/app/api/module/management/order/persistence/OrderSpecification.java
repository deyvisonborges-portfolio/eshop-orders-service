package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderSpecification {
  public Query buildQuery(final List<String> terms) {
    final var query = new Query();
    if (terms != null && !terms.isEmpty()) {
      for (String term : terms) {
        if (term != null && !term.isBlank()) {
          Criteria criteria = new Criteria();
          criteria.orOperator(
            Criteria.where("id").regex(term, "i"),
            Criteria.where("status").regex(term, "i"),
            Criteria.where("customerId").regex(term, "i")
          );
          query.addCriteria(criteria);
        }
      }
    }
    return query;
  }
}
