package com.rest.order.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.rest.order.model.Payment;
import com.rest.order.service.PaymentService;

@RestController
@RequestMapping("/thezone")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @GetMapping("/payment")
    public ResponseEntity<List<Payment>> list() {
        return new ResponseEntity<List<Payment>>(paymentService.list(), HttpStatus.OK);
    }

    @GetMapping("/payment/{customerNumber}")
    public ResponseEntity<List<Payment>> get(@PathVariable Integer customerNumber) {
        return new ResponseEntity<List<Payment>>(paymentService.get(customerNumber), HttpStatus.OK);
    }

    @GetMapping("/payment/{customerNumber}/{checkNumber}")
    public ResponseEntity<Payment> get(@PathVariable Integer customerNumber, @PathVariable String checkNumber) {
        return new ResponseEntity<Payment>(paymentService.get(customerNumber, checkNumber), HttpStatus.OK);
    }

    @GetMapping("/payment/{customerNumber}/{fromDate}/{toDate}")
    public ResponseEntity<List<Payment>> get(@PathVariable Integer customerNumber,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate) {
        return new ResponseEntity<List<Payment>>(paymentService.get(customerNumber, fromDate, toDate), HttpStatus.OK);
    }

    @PostMapping("/payment")
    public ResponseEntity<Payment> add(@RequestBody Payment payment) {
        return new ResponseEntity<>(paymentService.add(payment), HttpStatus.CREATED);
    }

    @PutMapping("/payment/{customerNumber}/{checkNumber}")
    public ResponseEntity<Payment> update(@PathVariable("customerNumber") Integer customerNumber,
            @PathVariable("checkNumber") String checkNumber, @RequestBody Payment payment) {
        return new ResponseEntity<>(paymentService.update(customerNumber, checkNumber, payment), HttpStatus.OK);
    }

    @DeleteMapping("/payment/{customerNumber}/{checkNumber}")
    public ResponseEntity<?> delete(@PathVariable("customerNumber") Integer customerNumber,
            @PathVariable("checkNumber") String checkNumber) {
        return new ResponseEntity<>(paymentService.delete(customerNumber, checkNumber), HttpStatus.OK);
    }

}