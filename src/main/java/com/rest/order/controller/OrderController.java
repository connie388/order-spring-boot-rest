package com.rest.order.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.order.model.Order;
import com.rest.order.model.OrderCustomerDTO;
import com.rest.order.service.OrderService;

@RestController
@RequestMapping("/thezone")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<List<Order>> list() {
        return new ResponseEntity<List<Order>>(orderService.list(), HttpStatus.OK);
    }

    @GetMapping("/order/{orderNumber}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<Optional<Order>> get(@PathVariable Integer orderNumber) {
        return new ResponseEntity<Optional<Order>>(orderService.get(orderNumber), HttpStatus.OK);
    }

    @GetMapping("/order/{status}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<List<Order>> get(@PathVariable String status) {
        return new ResponseEntity<List<Order>>(orderService.get(status), HttpStatus.OK);
    }

    @GetMapping("/order/{fromDate}/{toDate}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<List<Order>> get(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate) {
        return new ResponseEntity<List<Order>>(orderService.get(fromDate, toDate), HttpStatus.OK);
    }

    @GetMapping("{customerNo}/order")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<List<Order>> get(@PathVariable Integer customerNo,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate) {
        return new ResponseEntity<List<Order>>(orderService.get(customerNo, fromDate, toDate), HttpStatus.OK);
    }

    @GetMapping("/order/customer")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<List<OrderCustomerDTO>> get(@RequestParam(required = false) String customerNameLike,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date toDate) {
        return new ResponseEntity<List<OrderCustomerDTO>>(orderService.get(customerNameLike, fromDate, toDate),
                HttpStatus.OK);
    }

    @PostMapping("/order")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<Order> add(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.add(order), HttpStatus.CREATED);
    }

    @PutMapping("/order/{orderNumber}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<Order> update(@PathVariable("orderNumber") Integer orderNumber,
            @RequestBody Order order) {
        return new ResponseEntity<>(orderService.update(orderNumber, order), HttpStatus.OK);
    }
}
