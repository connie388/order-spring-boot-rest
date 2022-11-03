package com.rest.order.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.order.model.Customer;
import com.rest.order.service.CustomerService;

@RestController
@RequestMapping("/thezone")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/customer")
    public ResponseEntity<List<Customer>> list(@RequestParam(required = false) String name) {
        return new ResponseEntity<List<Customer>>(customerService.list(name), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Optional<Customer>> get(@PathVariable Integer id) {
        return new ResponseEntity<Optional<Customer>>(customerService.get(id), HttpStatus.OK);
    }

    @GetMapping("/customer/firstnamelike")
    public ResponseEntity<List<Customer>> get(@RequestParam(required = true) String firstName) {
        return new ResponseEntity<List<Customer>>(customerService.get(firstName), HttpStatus.OK);
    }

    @PostMapping("/customer")
    public ResponseEntity<Customer> add(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.add(customer), HttpStatus.CREATED);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") Integer id,
            @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.update(id, customer), HttpStatus.OK);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(customerService.delete(id), HttpStatus.OK);
    }
}