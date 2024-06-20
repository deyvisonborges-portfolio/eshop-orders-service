package com.deyvisonborges.service.orders.core.modules.management.order;

import com.deyvisonborges.service.orders.app.api.module.management.order.persistence.pagination.OrderFilter;
import com.deyvisonborges.service.orders.core.domain.pagination.SearchDirection;

public record OrderPaginationQuery(
  int page,
	int perPage,
	String sort,
	SearchDirection direction,
  OrderFilter filter
) {}
