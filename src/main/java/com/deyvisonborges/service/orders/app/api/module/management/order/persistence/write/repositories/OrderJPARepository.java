package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.write.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.write.entities.OrderJPAEntity;

public interface OrderJPARepository extends JpaRepository<OrderJPAEntity, String>{}