package com.rest.order.dao;

import java.util.List;

import com.rest.order.model.Product;

public interface ProductDAO {
    Product getProductByCode(String productCode);

    List<Product> getAllProducts();
}