package com.rest.order.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.order.exception.InvalidInputException;
import com.rest.order.exception.ResourceAlreadyExistsException;
import com.rest.order.exception.ResourceNotFoundException;
import com.rest.order.model.Office;
import com.rest.order.repository.OfficeRepository;

@Service
@Transactional
public class OfficeService {
    @Autowired
    private OfficeRepository officeRepository;

    public List<Office> list() {
        return officeRepository.findAll();
    }

    public Office get(String officeCode) {
        if (null == officeCode)
            throw new InvalidInputException("Invalid Office Code.");

        return officeRepository.findByOfficeCode(officeCode);
    }

    public Office add(Office office) {
        Office existingOffice = officeRepository.findByOfficeCode(office.getOfficeCode());
        if (existingOffice != null)
            throw new ResourceAlreadyExistsException("Office already exists.");
        return officeRepository.save(office);
    }

    public Office update(String officeCode, Office office) {
        if (null == officeCode)
            throw new InvalidInputException("Invalid Office Code");
        Office _office = officeRepository.findByOfficeCode(officeCode);
        if (_office == null)
            throw new ResourceNotFoundException("Office " + officeCode + " not found");

        _office.setCity(office.getCity());
        _office.setPhone(office.getPhone());
        _office.setAddressLine1(office.getAddressLine1());
        _office.setAddressLine2(office.getAddressLine2());
        _office.setState(office.getState());
        _office.setPostalCode(office.getPostalCode());
        _office.setCountry(office.getCountry());

        return officeRepository.save(_office);
    }

    public String delete(String officeCode) {
        if (null == officeCode)
            throw new InvalidInputException("Invalid Office Code");

        Office _office = officeRepository.findByOfficeCode(officeCode);
        if (_office == null)
            throw new ResourceNotFoundException("Office " + officeCode + " not found.");
        officeRepository.delete(_office);
        return "Office is deleted successfully";
    }
}