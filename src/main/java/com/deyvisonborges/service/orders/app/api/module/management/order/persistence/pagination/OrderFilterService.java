package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.pagination;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
        keyValue -> keyValue.length > 1 ? keyValue[1] : ""
      ));

    filterMap.forEach((key, value) -> {
      switch (key) {
        case "status" -> {
          final var statuses = Arrays.stream(value.split(","))
            .collect(Collectors.toList());
          filter.setStatuses(statuses);
        }
        case "startDate" -> filter.setStartDate(Instant.parse(value));
        case "endDate" -> filter.setEndDate(Instant.parse(value));
      }
    });

    return filter;
  }

  public static Query buildQueryFilter(OrderFilter filter) {
    List<Criteria> criteriaList = new ArrayList<>();

    if (filter.getStatuses() != null && !filter.getStatuses().isEmpty()) {
      criteriaList.add(Criteria.where("status").in(filter.getStatuses()));
    }

    if (filter.getStartDate() != null && filter.getEndDate() != null) {
      criteriaList.add(Criteria.where("orderDate").gte(filter.getStartDate()).lte(filter.getEndDate()));
    } else if (filter.getStartDate() != null) {
      criteriaList.add(Criteria.where("orderDate").gte(filter.getStartDate()));
    } else if (filter.getEndDate() != null) {
      criteriaList.add(Criteria.where("orderDate").lte(filter.getEndDate()));
    }

    Criteria combinedCriteria = new Criteria();
    if (!criteriaList.isEmpty()) {
      combinedCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
    }

    return new Query(combinedCriteria);
  }
}
