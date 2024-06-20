//package com.deyvisonborges.service.orders.app.api.module.management.order.persistence.pagination;
//
//import com.deyvisonborges.service.orders.core.modules.management.order.OrderStatus;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//
//import java.time.Instant;
//import java.util.List;
//
//public final class OrderSpecifications {
//  public static Query hasStatusIn(final List<OrderStatus> statusList) {
//    return new Query(Criteria.where("status").in(statusList));
//  }
//
//  public static Query hasOrderDateBetween(final Instant startDate, final Instant endDate) {
//    return new Query(Criteria.where("orderDate").gte(startDate).lte(endDate));
//  }
//}
