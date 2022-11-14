package com.rest.order.service;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.order.exception.InvalidInputException;
import com.rest.order.exception.ResourceNotFoundException;
import com.rest.order.model.Product;
import com.rest.order.model.ProductLine;
import com.rest.order.repository.ProductRepository;
import com.rest.order.repository.ProductLineRepository;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductLineRepository productLineRepository;

    public Product add(Product productInfo) {
        if (productInfo == null) {
            throw new InvalidInputException("Product Information is empty");
        }

        if (productInfo.getProductLine() == null) {
            throw new InvalidInputException("Product Line is invalid");
        }
        ProductLine _productLine = productLineRepository.findByProductLine(productInfo.getProductLine());
        if (_productLine == null)
            throw new ResourceNotFoundException("Product Line no found.");
        productInfo.setProdLine(_productLine);
        return productRepository.save(productInfo);
    }

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product get(String productCode) {
        if (null == productCode)
            throw new InvalidInputException("Invalid Product Code");
        return productRepository.findByProductCode(productCode);
    }

    public List<Product> getByProductLine(String productLine) {
        if (null == productLine)
            throw new InvalidInputException("Invalid Product Line");
        return productRepository.findByProductLine(productLine);
    }

    public String delete(String productCode) {
        if (null == productCode)
            throw new InvalidInputException("Invalid Product Code");

        Product _product = productRepository.findByProductCode(productCode);
        if (_product == null)
            throw new ResourceNotFoundException("Product Code " + productCode + " not found.");
        productRepository.deleteById(productCode);
        return "Product is deleted successfully";
    }

    public Product update(String productCode,
            Product productInfo) {
        if (null == productCode)
            throw new InvalidInputException("Invalid Product Code");

        Product _product = productRepository.findByProductCode(productCode);
        if (_product == null)
            throw new ResourceNotFoundException("Product Code " + productCode + " not found.");
        _product.setProdLine(productInfo.getProdLine());
        _product.setProductName(productInfo.getProductName());
        _product.setProductScale(productInfo.getProductScale());
        _product.setProductVendor(productInfo.getProductVendor());
        _product.setProductDesc(productInfo.getProductDesc());
        _product.setQuantityInStock(productInfo.getQuantityInStock());
        _product.setBuyPrice(productInfo.getBuyPrice());
        _product.setMsrp(productInfo.getMsrp());
        return productRepository.save(_product);
    }
}