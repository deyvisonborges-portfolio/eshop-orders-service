package com.deyvisonborges.service.orders.app.api.module.management.order.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPARepository extends JpaRepository<OrderJPAEntity, UUID>{}
