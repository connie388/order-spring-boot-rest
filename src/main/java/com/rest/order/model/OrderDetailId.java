package com.rest.order.model;

import java.io.Serializable;
import java.util.Objects;

public class OrderDetailId implements Serializable {
    private Integer orderNumber;

    private String productCode;

    public OrderDetailId() {
    }

    public OrderDetailId(Integer orderNumber, String productCode) {
        this.orderNumber = orderNumber;
        this.productCode = productCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrderDetailId orderDetailId = (OrderDetailId) o;
        return orderNumber.equals(orderDetailId.orderNumber) &&
                productCode.equals(orderDetailId.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNumber, productCode);
    }
}
