package com.rest.order.model;

import java.io.Serializable;
import java.util.Objects;

public class PaymentId implements Serializable {
    private Integer customerNumber;

    private String checkNumber;

    public PaymentId() {
    }

    public PaymentId(Integer customerNumber, String checkNumber) {
        this.customerNumber = customerNumber;
        this.checkNumber = checkNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PaymentId paymentId = (PaymentId) o;
        return customerNumber.equals(paymentId.customerNumber) &&
                checkNumber.equals(paymentId.checkNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerNumber, checkNumber);
    }

}
