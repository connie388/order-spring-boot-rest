package com.rest.order.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.order.exception.InvalidInputException;
import com.rest.order.exception.ResourceNotFoundException;
import com.rest.order.model.Order;
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

    public OrderDetail add(Integer orderNumber, OrderDetail orderDetail) {
        if (null == orderNumber)
            throw new InvalidInputException("Invalid Order Number.");

        Optional<Order> _order = orderRepository.findById(orderNumber);
        if (_order.isEmpty())
            throw new ResourceNotFoundException("Order record not found");
        Order thisOrder = _order.get();

        if (productRepository.findByProductCode(orderDetail.getProductCode()) == null)
            throw new ResourceNotFoundException("Invalid Product Code");
        orderDetail.setOrder(thisOrder);
        return orderDetailRepository.save(orderDetail);
    }

    public List<OrderDetail> add(Integer orderNumber, List<OrderDetail> items) {
        if (null == orderNumber)
            throw new InvalidInputException("Invalid Order Number.");

        Optional<Order> _order = orderRepository.findById(orderNumber);
        if (_order.isEmpty())
            throw new ResourceNotFoundException("Order record not found");
        Order thisOrder = _order.get();
        for (OrderDetail item : items)
            item.setOrder(thisOrder);
        return orderDetailRepository.saveAll(items);
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

    public String delete(Integer orderNumber) {
        if (null == orderNumber)
            throw new InvalidInputException("Invalid Order Number.");
        List<OrderDetail> _orderDetailList = orderDetailRepository.findOrderDetailByOrderNumber(orderNumber);
        if (_orderDetailList == null)
            throw new ResourceNotFoundException("Order Detail records not found");
        orderDetailRepository.deleteAll(_orderDetailList);
        return "Order Detail records for Order Number " + orderNumber + " are deleted successfully";
    }
}