package com.rest.order.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ProductMapper implements RowMapper<Product> {
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setProductCode(resultSet.getString("productCode"));
        product.setProductName(resultSet.getString("productName"));
        product.setProductScale(resultSet.getString("productScale"));
        product.setProductVendor(resultSet.getString("productVendor"));
        product.setProductDesc(resultSet.getString("productDescription"));
        product.setQuantityInStock(resultSet.getInt("quantityInStock"));
        product.setBuyPrice(resultSet.getBigDecimal("buyPrice"));
        product.setMsrp(resultSet.getBigDecimal("MSRP"));
        ProductLine productLine = new ProductLine();
        productLine.setProductLine(resultSet.getString("productLine"));
        productLine.setHtmlDesc(resultSet.getString("htmlDescription"));
        productLine.setTextDesc(resultSet.getString("textDescription"));
        productLine.setImageUrl(resultSet.getString("imageUrl"));
        product.setProdLine(productLine);
        return product;
    }
}
