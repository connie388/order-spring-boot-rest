package com.rest.order.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rest.order.model.Order;
import com.rest.order.model.OrderCustomerDTO;

public interface OrderRepository extends JpaRepository<Order, Integer>, OrderRepositoryCustom {
    List<Order> findByCustomerCustomerNumber(Integer customerNumber);

    List<Order> findOrderByStatus(String status);

    List<Order> findByCustomerCustomerNumberAndOrderDate(Integer customerNumber, LocalDate orderDate);

    @Query("SELECT new com.rest.order.model.OrderCustomerDTO(e.customerNumber, e.customerName, d.orderNumber, d.orderDate, d.requiredDate, d.shippedDate, d.status, d.comments, d.createdAt, d.updatedAt) "
            + "FROM Order d INNER JOIN d.customer e WHERE e.customerName like %?1% and (?2 is null or d.orderDate >= ?2) and (?3 is null or d.orderDate <= ?3)")
    List<OrderCustomerDTO> fetchCustomerOrders(String customerNameLike, LocalDate fromDate, LocalDate toDate);

    // Order findByOrderNumberOrderByOrderDetailList_OrderLineNumber(Integer
    // orderNumber);
}
