package com.rest.order.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.order.exception.InvalidInputException;
import com.rest.order.exception.ResourceAlreadyExistsException;
import com.rest.order.exception.ResourceNotFoundException;
import com.rest.order.model.Employee;
import com.rest.order.model.Office;
import com.rest.order.repository.EmployeeRepository;
import com.rest.order.repository.EmployeeSpecifications;
import com.rest.order.repository.OfficeRepository;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private OfficeRepository officeRepository;

    public List<Employee> list() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> get(Integer id) {
        if (null == id)
            throw new InvalidInputException("Invalid Employee Id");

        return employeeRepository.findById(id);
    }

    public List<Employee> get(String firstName) {
        if (null == firstName)
            throw new InvalidInputException("Invalid First Name");

        return employeeRepository.findAll(EmployeeSpecifications.hasFirstNameLike(firstName));
    }

    public Employee add(String officeCode, Integer reportsToId, Employee employee) {
        if (null == officeCode)
            throw new InvalidInputException("Invalid Office Code");
        Office office = officeRepository.findByOfficeCode(officeCode);
        if (office == null)
            throw new ResourceNotFoundException("Office Code not found");
        if (reportsToId == null)
            throw new InvalidInputException("Invalid Employee Reports To Id");
        Optional<Employee> reportsTo = employeeRepository.findById(reportsToId);
        if (reportsTo.isEmpty())
            throw new ResourceNotFoundException("Employee Reports To not found");
        Employee existingemployee = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if (existingemployee != null)
            throw new ResourceAlreadyExistsException("employee already exists.");

        employee.setOffice(office);
        employee.setReportsTo(reportsTo.get());
        return employeeRepository.save(employee);
    }

    public Employee update(Integer id, String officeCode, Integer reportsToId, Employee employee) {
        if (null == id)
            throw new InvalidInputException("Invalid Employee Id");

        return employeeRepository.findById(id).map(_employee -> {
            if (null == officeCode)
                throw new InvalidInputException("Invalid Office Code");
            Office office = officeRepository.findByOfficeCode(officeCode);
            if (office == null)
                throw new ResourceNotFoundException("Office Code not found");
            if (reportsToId == null)
                throw new InvalidInputException("Invalid Employee Reports To Id");
            Optional<Employee> reportsTo = employeeRepository.findById(reportsToId);
            if (reportsTo.isEmpty())
                throw new ResourceNotFoundException("Employee Reports To not found");

            _employee.setFirstName(employee.getFirstName());
            _employee.setLastName(employee.getLastName());
            _employee.setExtension(employee.getExtension());
            _employee.setEmail(employee.getEmail());
            _employee.setOffice(office);
            _employee.setReportsTo(reportsTo.get());
            _employee.setJobTitle(employee.getJobTitle());

            return employeeRepository.save(_employee);
        }).orElseThrow(() -> new ResourceNotFoundException("employee Id " + id + " not found"));
    }

    public String delete(Integer id) {
        if (null == id)
            throw new InvalidInputException("Invalid Employee Id");
        return employeeRepository.findById(id).map(_employee -> {
            employeeRepository.delete(_employee);
            return "Employee is deleted successfully";
        }).orElseThrow(() -> new ResourceNotFoundException("Employee Id " + id + " not found"));
    }
}