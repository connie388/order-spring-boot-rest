package com.rest.order.repository;

import java.util.List;
import java.util.Set;

import com.rest.order.model.Customer;

public interface CustomerRepositoryCustom {
    List<Customer> findCustomerByPhones(Set<String> phones);
}
