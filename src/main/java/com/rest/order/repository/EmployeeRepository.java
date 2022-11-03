package com.rest.order.repository;

// import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rest.order.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
    // List<Employee> findByReportsTo(Integer reportsTo);
    Employee findEmployeeByEmail(String email);
}