package com.rest.order.repository;

import javax.persistence.criteria.Join;

import org.springframework.data.jpa.domain.Specification;

import com.rest.order.model.Order;
import com.rest.order.model.Customer;

public class OrderSpecifications {

    public static Specification<Order> hasCustomerNameLike(String name) {
        return (root, query, criteriaBuilder) -> {
            Join<Customer, Order> ordersCustomer = root.join("customer");
            return criteriaBuilder.like(ordersCustomer.get("customerName"), "%" + name + "%");
        };
    }
}
