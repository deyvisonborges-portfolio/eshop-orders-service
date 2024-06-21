package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.pagination;

import java.time.Instant;
import java.util.List;
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
