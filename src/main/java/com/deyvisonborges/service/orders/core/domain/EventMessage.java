package com.deyvisonborges.service.orders.core.domain;

import java.time.Instant;
import java.util.UUID;

public abstract class EventMessage {
  private String id = UUID.randomUUID().toString();
  private int version = 1;
  private String title;
  private Instant start;
  private Instant end;

  public String getId() {
    return this.id;
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
}
