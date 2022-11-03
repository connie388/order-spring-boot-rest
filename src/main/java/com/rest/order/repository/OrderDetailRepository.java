package com.rest.order.repository;

import com.rest.order.model.OrderDetail;
import com.rest.order.model.OrderDetailId;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
    List<OrderDetail> findOrderDetailByOrderNumber(Integer orderNumber);

    List<OrderDetail> findByProductCode(String productCode);

    OrderDetail findOrderDetailByOrderNumberAndProductCode(Integer orderNumber, String productCode);
}
