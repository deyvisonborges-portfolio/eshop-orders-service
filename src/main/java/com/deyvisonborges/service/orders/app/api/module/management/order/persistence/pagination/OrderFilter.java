package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.pagination;

import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrderFilter {
  private List<String> statuses;
  private Instant startDate;
  private Instant endDate;
  
  public List<String> getStatuses() {
    return statuses;
  }

  public void setStatuses(List<String> statuses) {
    this.statuses = statuses;
  }

  public Instant getStartDate() {
    return startDate;
  }

  public void setStartDate(Instant startDate) {
    this.startDate = startDate;
  }

  public Instant getEndDate() {
    return endDate;
  }

  public void setEndDate(Instant endDate) {
    this.endDate = endDate;
  }
}
