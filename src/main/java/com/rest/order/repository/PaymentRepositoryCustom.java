package com.rest.order.repository;

import java.util.List;
import java.util.Date;

import com.rest.order.model.Payment;

public interface PaymentRepositoryCustom {
    List<Payment> findOutstandingPaymentsByCustomer(Integer customerNumber, Date fromDate, Date toDate);
}
