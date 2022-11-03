package com.rest.order.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.rest.order.model.Product;
import com.rest.order.model.ProductMapper;

@Component
public class ProductDAOImpl implements ProductDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SQL_FIND_PRODUCT = "SELECT pl.productLine, pl.textDescription, pl.htmlDescription, pl.image, p.productCode, p.productName, p.productScale, p.productVendor, p.productDescription, p.quantityInStock, p.buyPrice, p.MSRP  FROM productlines pl, products p where p.productline = pl.productline and p.productCode = ?";
    private final String SQL_GET_ALL = "SELECT pl.productLine, pl.textDescription, pl.htmlDescription, pl.image, p.productCode, p.productName, p.productScale, p.productVendor, p.productDescription, p.quantityInStock, p.buyPrice, p.MSRP  FROM productlines pl, products p where p.productline = pl.productline;";

    @Autowired
    public ProductDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Product getProductByCode(String productCode) {
        return (Product) jdbcTemplate.queryForObject(SQL_FIND_PRODUCT, new ProductMapper(),
                new Object[] { productCode });
    }

    public List<Product> getAllProducts() {
        return jdbcTemplate.query(SQL_GET_ALL, new ProductMapper());
    }
}