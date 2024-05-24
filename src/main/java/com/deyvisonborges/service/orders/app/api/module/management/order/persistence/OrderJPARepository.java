package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
// import java.time.Instant;

public interface OrderJPARepository extends JpaRepository<OrderJPAEntity, String>{
  Page<OrderJPAEntity> findAll(Specification<OrderJPAEntity> whereClause, Pageable page);
  // Page<OrderJPAEntity> findByCreatedAt(Instant createdAt, Pageable page);
}