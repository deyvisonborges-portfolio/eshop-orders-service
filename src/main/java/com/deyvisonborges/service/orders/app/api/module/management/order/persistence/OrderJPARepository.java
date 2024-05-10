package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPARepository extends JpaRepository<OrderJPAEntity, String>{}