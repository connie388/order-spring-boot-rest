package com.rest.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import com.fasterxml.jackson.databind.ser.FilterProvider;
// import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
// import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.rest.order.exception.InvalidInputException;
import com.rest.order.exception.ResourceAlreadyExistsException;
import com.rest.order.exception.ResourceNotFoundException;
import com.rest.order.model.Customer;
import com.rest.order.repository.CustomerRepository;
import com.rest.order.repository.CustomerSpecifications;
// import org.springframework.http.converter.json.MappingJacksonValue;

@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> list(String name) {
        List<Customer> customers = new ArrayList<Customer>();

        if (name == null)
            customerRepository.findAll().forEach(customers::add);
        else
            customerRepository.findByCustomerNameContaining(name).forEach(customers::add);
        return customers;
    }

    // public MappingJacksonValue list(String name, String[] filter) {
    // List<Customer> customers = new ArrayList<Customer>();

    // if (name == null)
    // customerRepository.findAll().forEach(customers::add);
    // else
    // customerRepository.findByCustomerNameContaining(name).forEach(customers::add);

    // SimpleBeanPropertyFilter simpleBeanPropertyFilter;
    // if (filter == null) {
    // simpleBeanPropertyFilter = SimpleBeanPropertyFilter
    // .serializeAllExcept("");
    // } else {
    // simpleBeanPropertyFilter = SimpleBeanPropertyFilter
    // .serializeAllExcept(filter);
    // }

    // // Assume customerFilter is defined in Customer model
    // FilterProvider filterProvider = new SimpleFilterProvider()
    // .addFilter("customerFilter", simpleBeanPropertyFilter);

    // MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(customers);
    // mappingJacksonValue.setFilters(filterProvider);
    // return mappingJacksonValue;
    // }

    public Optional<Customer> get(Integer id) {
        if (null == id)
            throw new InvalidInputException("Invalid Customer Id");

        return customerRepository.findById(id);
    }

    public List<Customer> get(String firstName) {
        if (null == firstName)
            throw new InvalidInputException("Invalid First Name.");

        return customerRepository.findAll(CustomerSpecifications.hasFirstNameLike(firstName));
    }

    public Customer add(Customer customer) {
        Customer existingCustomer = customerRepository.findCustomerByPhoneAndCustomerName(customer.getPhone(),
                customer.getCustomerName());
        if (existingCustomer != null)
            throw new ResourceAlreadyExistsException("Customer already exists.");

        return customerRepository.save(customer);
    }

    public Customer update(Integer id, Customer customer) {
        if (null == id)
            throw new InvalidInputException("Invalid Customer Id");

        return customerRepository.findById(id).map(_customer -> {
            _customer.setCustomerName(customer.getCustomerName());
            _customer.setContactFirstName(customer.getContactFirstName());
            _customer.setContactLastName(customer.getContactLastName());
            _customer.setPhone(customer.getPhone());
            _customer.setAddressLine1(customer.getAddressLine1());
            _customer.setAddressLine2(customer.getAddressLine2());
            _customer.setCity(customer.getCity());
            _customer.setState(customer.getState());
            _customer.setPostalCode(customer.getPostalCode());
            _customer.setCountry(customer.getCountry());
            _customer.setSalesRepEmployeeNumber(customer.getSalesRepEmployeeNumber());
            _customer.setCreditLimit(customer.getCreditLimit());
            return customerRepository.save(_customer);
        }).orElseThrow(() -> new ResourceNotFoundException("Customer Id " + id + " not found"));
    }

    public String delete(Integer id) {
        if (null == id)
            throw new InvalidInputException("Invalid Customer Id");

        return customerRepository.findById(id).map(_customer -> {
            customerRepository.delete(_customer);
            return "Customer is deleted successfully";
        }).orElseThrow(() -> new ResourceNotFoundException("Customer Id " + id + " not found"));
    }
}