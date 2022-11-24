package com.rest.order.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class OrderDetail {
    private String productCode;
    private Integer quantityOrdered;

    private BigDecimal priceEach;

    private Short orderLineNumber;
}