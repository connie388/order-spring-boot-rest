package com.rest.order.repository;

import org.springframework.data.jpa.domain.Specification;

import com.rest.order.model.Customer;

public class CustomerSpecifications {

    public static Specification<Customer> hasFirstNameLike(String name) {
        return (root, query, cb) -> cb.like(root.<String>get("contactFirstName"),
                "%" + name + "%");
    }

    public static Specification<Customer> hasLastName(String name) {
        return (root, query, cb) -> cb.equal(root.<String>get("contactLastName"), name);
    }

    static Specification<Customer> hasLastNameLike(String name) {
        return (root, cq, cb) -> cb.like(root.<String>get("contactLastName"), "%" + name + "%");
    }
}
