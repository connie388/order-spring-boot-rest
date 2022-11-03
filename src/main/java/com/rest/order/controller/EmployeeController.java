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

import com.rest.order.model.Employee;
import com.rest.order.service.EmployeeService;

@RestController
@RequestMapping("/thezone")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> list() {
        return new ResponseEntity<List<Employee>>(employeeService.list(), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Optional<Employee>> get(@PathVariable Integer id) {
        return new ResponseEntity<Optional<Employee>>(employeeService.get(id), HttpStatus.OK);
    }

    @GetMapping("/employee/firstnamelike")
    public ResponseEntity<List<Employee>> get(@RequestParam(required = true) String firstName) {
        return new ResponseEntity<List<Employee>>(employeeService.get(firstName), HttpStatus.OK);
    }

    @PostMapping("/employee/{officeCode}/{reportsToId}")
    public ResponseEntity<Employee> add(@PathVariable("officeCode") String officeCode,
            @PathVariable("reportsToId") Integer reportsToId, @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.add(officeCode, reportsToId, employee), HttpStatus.CREATED);
    }

    @PutMapping("/employee/{id}/{officeCode}/{reportsToId}")
    public ResponseEntity<Employee> update(@PathVariable("id") Integer id,
            @PathVariable("officeCode") String officeCode,
            @PathVariable("reportsToId") Integer reportsToId,
            @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.update(id, officeCode, reportsToId, employee),
                HttpStatus.OK);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return new ResponseEntity<>(employeeService.delete(id), HttpStatus.OK);
    }
}