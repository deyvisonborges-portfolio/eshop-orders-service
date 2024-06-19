package com.deyvisonborges.service.orders.core.modules.management.order;

import java.util.Map;

import com.deyvisonborges.service.orders.core.domain.pagination.SearchDirection;

public record OrderPaginationQuery(
  int page,
	int perPage,
	Map<String, String> terms,
	String sort,
	SearchDirection direction
) {}
