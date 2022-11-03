package com.rest.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.order.model.ProductLine;

@Repository
public interface ProductLineRepository extends JpaRepository<ProductLine, String> {
    ProductLine findByProductLine(String productLine);
}