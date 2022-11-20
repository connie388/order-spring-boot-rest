package com.rest.order.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.order.model.OrderCustomerDTO;
import com.rest.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>, OrderRepositoryCustom {
    List<Order> findByCustomerCustomerNumber(Integer customerNumber);

    List<Order> findOrderByStatus(String status);

    List<Order> findByCustomerCustomerNumberAndOrderDate(Integer customerNumber, Date orderDate);

    @Query("SELECT new com.rest.order.model.OrderCustomerDTO(e.customerNumber, e.customerName, d.orderNumber, d.orderDate, d.requiredDate, d.shippedDate, d.status, d.comments, d.createdAt, d.updatedAt) "
            + "FROM Order d INNER JOIN d.customer e WHERE e.customerName like %?1% ")
    List<OrderCustomerDTO> fetchCustomerOrders(String customerNameLike);
}
