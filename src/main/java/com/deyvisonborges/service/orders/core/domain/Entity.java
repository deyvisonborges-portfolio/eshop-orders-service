package com.deyvisonborges.service.orders.core.domain;

import java.time.Instant;
import java.util.Objects;

public abstract class Entity<ID extends Identifier<ID>> {
  protected ID id;
  protected Boolean active;
  protected Instant createdAt;
  protected Instant updatedAt;

  protected Entity(final ID id, final Boolean active, final Instant createdAt, final Instant updatedAt) {
    Objects.requireNonNull(id, "'id' should not be null");
    this.id = id;
    this.active = active != null ? active : true;
    this.createdAt = createdAt != null ? createdAt : Instant.now();
    this.updatedAt = updatedAt != null ? updatedAt : Instant.now();
  }

  public ID getId() {
    return this.id;
  }

  public Boolean getActive() {
    return active;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    final Entity<?> entity = (Entity<?>) o;
    return Objects.equals(getId(), entity.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}