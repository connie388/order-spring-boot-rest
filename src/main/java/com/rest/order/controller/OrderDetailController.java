package com.rest.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<List<OrderDetail>> list() {
        return new ResponseEntity<List<OrderDetail>>(orderDetailService.list(), HttpStatus.OK);
    }

    @GetMapping("/orderdetail/{orderNumber}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<List<OrderDetail>> get(@PathVariable Integer orderNumber) {
        return new ResponseEntity<List<OrderDetail>>(orderDetailService.get(orderNumber), HttpStatus.OK);
    }

    @GetMapping("/orderdetail/{orderNumber}/{productCode}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<OrderDetail> get(@PathVariable Integer orderNumber, @PathVariable String productCode) {
        return new ResponseEntity<OrderDetail>(orderDetailService.get(orderNumber, productCode), HttpStatus.OK);
    }

    @PostMapping("/orderdetail/{orderNumber}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<OrderDetail> add(@PathVariable("orderNumber") Integer orderNumber,
            @RequestBody OrderDetail orderDetail) {
        return new ResponseEntity<>(orderDetailService.add(orderNumber, orderDetail), HttpStatus.CREATED);
    }

    @PostMapping("/orderdetail/{orderNumber}/list")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<List<OrderDetail>> add(@PathVariable("orderNumber") Integer orderNumber,
            @RequestBody List<OrderDetail> orderDetails) {
        return new ResponseEntity<>(orderDetailService.add(orderNumber, orderDetails), HttpStatus.CREATED);
    }

    @PutMapping("/orderdetail/{orderNumber}/{productCode}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<OrderDetail> update(@PathVariable("orderNumber") Integer orderNumber,
            @PathVariable("productCode") String productCode, @RequestBody OrderDetail orderDetail) {
        return new ResponseEntity<>(orderDetailService.update(orderNumber, productCode, orderDetail), HttpStatus.OK);
    }

    @DeleteMapping("/orderdetail/{orderNumber}/{productCode}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> delete(@PathVariable("orderNumber") Integer orderNumber,
            @PathVariable("productCode") String productCode) {
        return new ResponseEntity<>(orderDetailService.delete(orderNumber, productCode), HttpStatus.OK);
    }

    @DeleteMapping("/orderdetail/{orderNumber}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<?> delete(@PathVariable("orderNumber") Integer orderNumber) {
        return new ResponseEntity<>(orderDetailService.delete(orderNumber), HttpStatus.OK);
    }
}
