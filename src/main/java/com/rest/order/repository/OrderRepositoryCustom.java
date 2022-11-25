package com.rest.order.repository;

import java.time.LocalDate;
import java.util.List;

import com.rest.order.model.Order;

public interface OrderRepositoryCustom {
    List<Order> findOrderByRequiredDateRange( LocalDate fromDate, LocalDate toDate);
    List<Order> findOrderByIdAndRequiredDateRange( Integer id, LocalDate fromDate, LocalDate toDate);

}
