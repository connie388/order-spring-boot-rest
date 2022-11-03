package com.rest.order.repository;

import org.springframework.data.jpa.domain.Specification;

import com.rest.order.model.Employee;

public class EmployeeSpecifications {

    public static Specification<Employee> hasFirstNameLike(String name) {
        return (root, query, cb) -> cb.like(root.<String>get("firstName"),
                "%" + name + "%");
    }

    public static Specification<Employee> hasLastName(String name) {
        return (root, query, cb) -> cb.equal(root.<String>get("lastName"), name);
    }

    static Specification<Employee> hasLastNameLike(String name) {
        return (root, cq, cb) -> cb.like(root.<String>get("lastName"), "%" + name + "%");
    }
}
