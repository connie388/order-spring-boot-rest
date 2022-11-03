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

import com.rest.order.model.OrderDetail;
import com.rest.order.service.OrderDetailService;

@RestController
@RequestMapping("/thezone")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("/orderdetail")
    public ResponseEntity<List<OrderDetail>> list() {
        return new ResponseEntity<List<OrderDetail>>(orderDetailService.list(), HttpStatus.OK);
    }

    @GetMapping("/orderdetail/{orderNumber}")
    public ResponseEntity<List<OrderDetail>> get(@PathVariable Integer orderNumber) {
        return new ResponseEntity<List<OrderDetail>>(orderDetailService.get(orderNumber), HttpStatus.OK);
    }

    @GetMapping("/orderdetail/{orderNumber}/{productCode}")
    public ResponseEntity<OrderDetail> get(@PathVariable Integer orderNumber, @PathVariable String productCode) {
        return new ResponseEntity<OrderDetail>(orderDetailService.get(orderNumber, productCode), HttpStatus.OK);
    }

    @PostMapping("/orderdetail")
    public ResponseEntity<OrderDetail> add(@RequestBody OrderDetail orderDetail) {
        return new ResponseEntity<>(orderDetailService.add(orderDetail), HttpStatus.CREATED);
    }

    @PutMapping("/orderdetail/{orderNumber}/{productCode}")
    public ResponseEntity<OrderDetail> update(@PathVariable("orderNumber") Integer orderNumber,
            @PathVariable("productCode") String productCode, @RequestBody OrderDetail orderDetail) {
        return new ResponseEntity<>(orderDetailService.update(orderNumber, productCode, orderDetail), HttpStatus.OK);
    }

    @DeleteMapping("/orderdetail/{customerNumber}/{checkNumber}")
    public ResponseEntity<?> delete(@PathVariable("customerNumber") Integer customerNumber,
            @PathVariable("checkNumber") String checkNumber) {
        return new ResponseEntity<>(orderDetailService.delete(customerNumber, checkNumber), HttpStatus.OK);
    }
}
