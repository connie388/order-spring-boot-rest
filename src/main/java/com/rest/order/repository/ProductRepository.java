package com.rest.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.order.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByProductCode(String productCode);

    List<Product> findByProductNameContaining(String productName);
}