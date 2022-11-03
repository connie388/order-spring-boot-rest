package com.rest.order.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.order.exception.InvalidInputException;
import com.rest.order.exception.ResourceAlreadyExistsException;
import com.rest.order.exception.ResourceNotFoundException;
import com.rest.order.model.OrderDetail;
import com.rest.order.repository.OrderDetailRepository;
import com.rest.order.repository.OrderRepository;
import com.rest.order.repository.ProductRepository;

@Service
@Transactional
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<OrderDetail> list() {
        return orderDetailRepository.findAll();
    }

    public OrderDetail get(Integer orderNumber, String productCode) {
        if (null == orderNumber)
            throw new InvalidInputException("Invalid Order Number");
        if (null == productCode)
            throw new InvalidInputException("Invalid Product Code");

        return orderDetailRepository.findOrderDetailByOrderNumberAndProductCode(orderNumber, productCode);
    }

    public List<OrderDetail> get(Integer orderNumber) {
        if (null == orderNumber)
            throw new InvalidInputException("Invalid Order Number");

        return orderDetailRepository.findOrderDetailByOrderNumber(orderNumber);
    }

    public OrderDetail add(OrderDetail orderDetail) {
        OrderDetail existingOrderDetail = orderDetailRepository.findOrderDetailByOrderNumberAndProductCode(
                orderDetail.getOrderNumber(),
                orderDetail.getProductCode());
        if (existingOrderDetail != null)
            throw new ResourceAlreadyExistsException("Order Detail already exists.");

        if (orderRepository.findById(orderDetail.getOrderNumber()).isEmpty())
            throw new ResourceNotFoundException("Invalid Order Number");

        if (productRepository.findByProductCode(orderDetail.getProductCode()) == null)
            throw new ResourceNotFoundException("Invalid Product Code");

        return orderDetailRepository.save(orderDetail);
    }

    public OrderDetail update(Integer orderNumber, String productCode, OrderDetail orderDetail) {
        if (null == orderNumber)
            throw new InvalidInputException("Invalid Order Number.");
        if (null == productCode)
            throw new InvalidInputException("Invalid Product Code");
        OrderDetail _orderDetail = orderDetailRepository.findOrderDetailByOrderNumberAndProductCode(orderNumber,
                productCode);
        if (_orderDetail == null)
            throw new ResourceNotFoundException("Order Detail record not found");
        _orderDetail.setQuantityOrdered(_orderDetail.getQuantityOrdered());
        _orderDetail.setPriceEach(_orderDetail.getPriceEach());
        _orderDetail.setOrderLineNumber(_orderDetail.getOrderLineNumber());
        return orderDetailRepository.save(_orderDetail);
    }

    public String delete(Integer orderNumber, String productCode) {
        if (null == orderNumber)
            throw new InvalidInputException("Invalid Order Number.");
        if (null == productCode)
            throw new InvalidInputException("Invalid Product Code");
        OrderDetail _orderDetail = orderDetailRepository.findOrderDetailByOrderNumberAndProductCode(orderNumber,
                productCode);
        if (_orderDetail == null)
            throw new ResourceNotFoundException("Order Detail record not found");
        orderDetailRepository.delete(_orderDetail);
        return "Order Detail is deleted successfully";
    }
}