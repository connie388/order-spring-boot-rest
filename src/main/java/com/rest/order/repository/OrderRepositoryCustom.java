package com.rest.order.repository;

import java.util.List;
import java.util.Date;

import com.rest.order.model.Order;

public interface OrderRepositoryCustom {
    List<Order> findOrderByRequiredDateRange( Date fromDate, Date toDate);
}
