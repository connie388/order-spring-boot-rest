package com.rest.order.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.order.exception.InvalidInputException;
import com.rest.order.exception.ResourceNotFoundException;
import com.rest.order.model.Order;
import com.rest.order.repository.OrderRepository;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> list() {
        return orderRepository.findAll();
    }

    public Optional<Order> get(Integer orderNumber) {
        if (null == orderNumber)
            throw new InvalidInputException("Invalid Order Number");
        return orderRepository.findById(orderNumber);
    }

    public List<Order> get(String status) {
        if (null == status)
            throw new InvalidInputException("Invalid Status Code.");
        return orderRepository.findOrderByStatus(status);
    }

    public List<Order> get(Date fromDate, Date toDate) {
        if (null == fromDate)
            throw new InvalidInputException("Invalid From Date.");
        if (null == toDate)
            throw new InvalidInputException("Invalid To Date.");
        return orderRepository.findOrderByRequiredDateRange(fromDate, toDate);
    }

    public Order add(Order order) {
        return orderRepository.save(order);
    }

    public Order update(Integer orderNumber, Order order) {
        if (null == orderNumber)
            throw new InvalidInputException("Invalid Order Number.");

        Optional<Order> _order = orderRepository.findById(orderNumber);
        if (_order.isEmpty())
            throw new ResourceNotFoundException("Order record not found");
        Order thisOrder = _order.get();

        // Assume customer is not changeable
        thisOrder.setOrderDate(order.getOrderDate());
        thisOrder.setRequiredDate(order.getRequiredDate());
        thisOrder.setShippedDate(order.getShippedDate());
        thisOrder.setStatus(order.getStatus());
        thisOrder.setComments(order.getComments());
        return orderRepository.save(thisOrder);
    }
}