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

import com.rest.order.model.Product;
import com.rest.order.service.ProductService;

@RestController
@RequestMapping("/thezone")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("{productLine}/product")
    public ResponseEntity<Product> add(@PathVariable("productLine") String productLine,
            @RequestBody Product productInfo) {
        return new ResponseEntity<>(productService.add(productLine, productInfo), HttpStatus.CREATED);
    }

    @GetMapping("/product")
    ResponseEntity<List<Product>> list() {
        return new ResponseEntity<>(productService.list(), HttpStatus.OK);
    }

    @GetMapping("/product/{productCode}")
    public ResponseEntity<Product> get(@PathVariable String productCode) {
        return new ResponseEntity<Product>(productService.get(productCode), HttpStatus.OK);
    }

    @DeleteMapping("/product/{product}")
    public ResponseEntity<Object> delete(@PathVariable("productCode") String productCode) {
        return new ResponseEntity<>(productService.delete(productCode), HttpStatus.OK);
    }

    @PutMapping("/product/{product}")
    public ResponseEntity<Product> update(@PathVariable("productCode") String productCode,
            @RequestBody Product productInfo) {
        return new ResponseEntity<>(productService.update(productCode, productInfo), HttpStatus.OK);
    }
}