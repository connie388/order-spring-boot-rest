package com.rest.order.repository;

import com.rest.order.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CustomerRepository
        extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer>, CustomerRepositoryCustom {
    List<Customer> findByCustomerNameContaining(String name);

    Customer findCustomerByPhoneAndCustomerName(String phone, String customerName);
}