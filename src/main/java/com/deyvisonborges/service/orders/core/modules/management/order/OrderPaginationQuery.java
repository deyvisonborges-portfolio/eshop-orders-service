package com.deyvisonborges.service.orders.core.modules.management.order;

import com.deyvisonborges.service.orders.core.domain.pagination.old.SearchDirection;

public record OrderPaginationQuery(
  int page,
	int perPage,
	String terms,
	String sort,
	SearchDirection direction
) {}
