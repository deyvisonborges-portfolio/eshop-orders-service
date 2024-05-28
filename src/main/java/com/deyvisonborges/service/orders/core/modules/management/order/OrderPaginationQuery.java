package com.deyvisonborges.service.orders.core.modules.management.order;

import java.util.List;

import com.deyvisonborges.service.orders.core.domain.pagination.SearchDirection;

public record OrderPaginationQuery(
  int page,
	int perPage,
	List<String> terms,
	String sort,
	SearchDirection direction
) {}