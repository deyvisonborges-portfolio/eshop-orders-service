package com.deyvisonborges.service.orders.app.messaging.artifacts.events.listeners;

public class OrderEventData {
  private String id;
  private int version;
  private String title;
  private String start;
  private String end;
  private String orderId;
  private String status;

  public OrderEventData() {}

    public OrderEventData(String id, int version, String title, String start, String end, String orderId, String status) {
      this.id = id;
      this.version = version;
      this.title = title;
      this.start = start;
      this.end = end;
      this.orderId = orderId;
      this.status = status;
    }

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

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getEnd() {
    return end;
  }

  public void setEnd(String end) {
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

  @Override
  public String toString() {
    return "OrderEventData{" +
      "id='" + id + '\'' +
      ", version=" + version +
      ", title='" + title + '\'' +
      ", start='" + start + '\'' +
      ", end='" + end + '\'' +
      ", orderId='" + orderId + '\'' +
      ", status='" + status + '\'' +
      '}';
  }
}
