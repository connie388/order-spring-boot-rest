package com.rest.order.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.order.exception.InvalidInputException;
import com.rest.order.exception.ResourceNotFoundException;
import com.rest.order.model.ProductLine;
import com.rest.order.repository.ProductLineRepository;

@Service
@Transactional
public class ProductLineService {
    @Autowired
    private ProductLineRepository productLineRepository;

    public ProductLine add(ProductLine productLineInfo) {
        return productLineRepository.save(productLineInfo);
    }

    public List<ProductLine> list() {
        return productLineRepository.findAll();
    }

    public ProductLine get(String productLine) {
        if (null == productLine)
            throw new InvalidInputException("Invalid Product Line");

        return productLineRepository.findByProductLine(productLine);
    }

    public String delete(String productLine) {
        if (null == productLine)
            throw new InvalidInputException("Invalid Product Line");

        ProductLine _productLine = productLineRepository.findByProductLine(productLine);
        if (_productLine == null)
            throw new ResourceNotFoundException("Product Line " + productLine + " not found.");
        productLineRepository.deleteById(productLine);
        return "Product Line is deleted successfully";
    }

    public ProductLine update(String productLine,
            ProductLine productLineInfo) {
        if (null == productLine)
            throw new InvalidInputException("Invalid Product Line");

        ProductLine _productLine = productLineRepository.findByProductLine(productLine);
        if (_productLine == null)
            throw new ResourceNotFoundException("Product Line " + productLine + " not found.");
        _productLine.setTextDesc(productLineInfo.getTextDesc());
        _productLine.setHtmlDesc(productLineInfo.getHtmlDesc());
        _productLine.setImageUrl(productLineInfo.getImageUrl());
        return productLineRepository.save(_productLine);
    }
}