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

import com.rest.order.model.ProductLine;
import com.rest.order.service.ProductLineService;

@RestController
@RequestMapping("/thezone")
public class ProductLineController {
    @Autowired
    private ProductLineService productLineService;

    @PostMapping("/productline")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<ProductLine> add(@RequestBody ProductLine productLineInfo) {
        return new ResponseEntity<>(productLineService.add(productLineInfo), HttpStatus.CREATED);
    }

    @GetMapping("/productline")
    @CrossOrigin(origins = "http://localhost:8081")
    ResponseEntity<List<ProductLine>> list() {
        return new ResponseEntity<>(productLineService.list(), HttpStatus.OK);
    }

    @GetMapping("/productline/{productLine}")
    @CrossOrigin(origins = "http://localhost:8081")
    ResponseEntity<ProductLine> get(@PathVariable("productLine") String productLine) {
        return new ResponseEntity<>(productLineService.get(productLine), HttpStatus.OK);
    }

    @DeleteMapping("/productline/{productLine}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<Object> delete(@PathVariable("productLine") String productLine) {
        return new ResponseEntity<>(productLineService.delete(productLine), HttpStatus.OK);
    }

    @PutMapping("/productline/{productLine}")
    @CrossOrigin(origins = "http://localhost:8081")
    public ResponseEntity<ProductLine> update(@PathVariable("productLine") String productLine,
            @RequestBody ProductLine productLineInfo) {
        return new ResponseEntity<>(productLineService.update(productLine, productLineInfo), HttpStatus.OK);
    }
}