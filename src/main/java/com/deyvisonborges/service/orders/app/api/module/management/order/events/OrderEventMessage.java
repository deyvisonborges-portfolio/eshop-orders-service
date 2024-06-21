package com.deyvisonborges.service.orders.app.api.module.management.order.events;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderEventMessage {
  @JsonProperty("id")
  private String id;
  @JsonProperty("version")
  private int version;
  @JsonProperty("title")
  private String title;
  @JsonProperty("start")
  private Instant start;
  @JsonProperty("end")
  private Instant end;
  @JsonProperty("orderId")
  private String orderId;
  @JsonProperty("status")
  private String status;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Instant getStart() {
    return start;
  }

  public void setStart(Instant start) {
    this.start = start;
  }

  public Instant getEnd() {
    return end;
  }

  public void setEnd(Instant end) {
    this.end = end;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public OrderEventMessage() {
  }

  public OrderEventMessage(String id, int version, String title, Instant start, Instant end, String orderId,
      String status) {
    this.id = id;
    this.version = version;
    this.title = title;
    this.start = start;
    this.end = end;
    this.orderId = orderId;
    this.status = status;
  }

  @Override
  public String toString() {
    return "OrderEventMessage [id=" + id + ", version=" + version + ", title=" + title + ", start=" + start + ", end="
        + end + ", orderId=" + orderId + ", status=" + status + "]";
  }

}
