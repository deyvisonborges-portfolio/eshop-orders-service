package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.pagination;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderFilterService {

  public static OrderFilter parseFilters(String filters) {
    OrderFilter filter = new OrderFilter();

    if (filters == null || filters.isEmpty()) {
      return filter;
    }

    Map<String, String> filterMap = Arrays.stream(filters.split("&"))
      .map(entry -> entry.split("="))
      .collect(Collectors.toMap(
        keyValue -> keyValue[0],
        keyValue -> keyValue.length > 1 ? keyValue[1] : "",
        (existing, replacement) -> existing
      ));

    filterMap.forEach((key, value) -> {
      switch (key) {
        case "status" -> filter.setStatuses(Arrays.asList(value.split(",")));
        case "startDate" -> filter.setStartDate(parseInstant(value));
        case "endDate" -> filter.setEndDate(parseInstant(value));
      }
    });

    return filter;
  }

  private static Instant parseInstant(String value) {
    try {
      return Instant.parse(value);
    } catch (Exception e) {
      return null;
    }
  }

  public static Query buildQueryFilter(OrderFilter filter) {
    List<Criteria> criteriaList = new ArrayList<>();

    addCriteriaIfNotEmpty(criteriaList, "status", filter.getStatuses());
    addDateCriteria(criteriaList, "orderDate", filter.getStartDate(), filter.getEndDate());

    Criteria combinedCriteria = new Criteria();
    if (!criteriaList.isEmpty())
      combinedCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
    return new Query(combinedCriteria);
  }

  private static void addCriteriaIfNotEmpty(List<Criteria> criteriaList, String field, List<String> values) {
    if (values != null && !values.isEmpty()) {
      criteriaList.add(Criteria.where(field).in(values));
    }
  }

  private static void addDateCriteria(List<Criteria> criteriaList, String field, Instant startDate, Instant endDate) {
    if (startDate != null && endDate != null) {
      criteriaList.add(Criteria.where(field).gte(startDate).lte(endDate));
    } else if (startDate != null) {
      criteriaList.add(Criteria.where(field).gte(startDate));
    } else if (endDate != null) {
      criteriaList.add(Criteria.where(field).lte(endDate));
    }
  }
}
