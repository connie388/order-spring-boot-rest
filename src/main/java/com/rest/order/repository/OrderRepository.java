package com.rest.order.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>, OrderRepositoryCustom {
    List<Order> findByCustomerCustomerNumber(Integer customerNumber);

    List<Order> findOrderByStatus(String status);

    List<Order> findByCustomerCustomerNumberAndOrderDate(Integer customerNumber, Date orderDate);
}
