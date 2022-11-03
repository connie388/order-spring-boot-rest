package com.rest.order.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.rest.order.model.Office;
import com.rest.order.service.OfficeService;

@RestController
@RequestMapping("/thezone")
public class OfficeController {
    @Autowired
    private OfficeService officeService;

    @PostMapping("/office")
    public ResponseEntity<Office> save(@RequestBody Office OfficeInfo) {
        return new ResponseEntity<>(officeService.add(OfficeInfo), HttpStatus.CREATED);
    }

    @GetMapping("/office")
    ResponseEntity<List<Office>> list() {
        return new ResponseEntity<>(officeService.list(), HttpStatus.OK);
    }

    @DeleteMapping("/office/{officeCode}")
    public ResponseEntity<Object> delete(@PathVariable("officeCode") String officeCode) {
        return new ResponseEntity<>(officeService.delete(officeCode), HttpStatus.OK);
    }

    @PutMapping("/office/{officeCode}")
    public ResponseEntity<Office> update(@PathVariable("officeCode") String officeCode,
            @RequestBody Office officeInfo) {
        return new ResponseEntity<>(officeService.update(officeCode, officeInfo), HttpStatus.OK);
    }
}